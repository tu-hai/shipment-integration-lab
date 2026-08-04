package com.shipmentintegration.integration.service;

import com.shipmentintegration.integration.dto.CarrierBookingResponse;
import com.shipmentintegration.integration.dto.CarrierQuoteResponse;
import com.shipmentintegration.integration.dto.ShipmentRequest;
import com.shipmentintegration.integration.dto.ShipmentResponse;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ShipmentOrchestrationService {

    private static final Logger log = LoggerFactory.getLogger(ShipmentOrchestrationService.class);
    private final Map<String, ShipmentResponse> shipmentRepository = new ConcurrentHashMap<>();
    private final CarrierAdapterService carrierAdapterService;
    private final Counter shipmentTotalCounter;
    private final Counter shipmentSuccessCounter;
    private final Counter shipmentRetryCounter;

    public ShipmentOrchestrationService(CarrierAdapterService carrierAdapterService, MeterRegistry meterRegistry) {
        this.carrierAdapterService = carrierAdapterService;
        this.shipmentTotalCounter = Counter.builder("shipment_request_total")
                .description("Total shipment orchestration requests")
                .register(meterRegistry);
        this.shipmentSuccessCounter = Counter.builder("shipment_success_total")
                .description("Total successful shipments")
                .register(meterRegistry);
        this.shipmentRetryCounter = Counter.builder("shipment_retry_total")
                .description("Total shipment retries")
                .register(meterRegistry);
    }

    public ShipmentResponse processShipment(ShipmentRequest request) {
        shipmentTotalCounter.increment();
        String shipmentId = "SHP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        log.info("Starting shipment orchestration. shipmentId={}, orderId={}, preference={}", shipmentId, request.getOrderId(), request.getCarrierPreference());

        CarrierQuoteResponse quote = carrierAdapterService.fetchQuote("Hanoi", "VN");
        CarrierBookingResponse booking = carrierAdapterService.bookShipment(quote.getQuoteId(), request.getOrderId());

        ShipmentResponse response = new ShipmentResponse(
                shipmentId,
                request.getOrderId(),
                booking.getStatus(),
                quote.getCarrierName(),
                booking.getTrackingNumber(),
                Instant.now().toString()
        );

        shipmentRepository.put(shipmentId, response);
        shipmentSuccessCounter.increment();
        log.info("Shipment orchestration completed successfully. shipmentId={}, trackingNumber={}", shipmentId, response.getTrackingNumber());
        return response;
    }

    public ShipmentResponse retryShipment(String shipmentId) {
        shipmentRetryCounter.increment();
        log.info("Retrying shipment orchestration for shipmentId={}", shipmentId);
        ShipmentResponse existing = shipmentRepository.get(shipmentId);
        if (existing == null) {
            existing = new ShipmentResponse(
                    shipmentId,
                    "ORD-RETRY-001",
                    "PROCESSING",
                    "FAST_SHIP_EXPRESS",
                    "TRK-RETRY-" + UUID.randomUUID().toString().substring(0, 6),
                    Instant.now().toString()
            );
        }
        existing.setStatus("CONFIRMED_AFTER_RETRY");
        shipmentRepository.put(shipmentId, existing);
        return existing;
    }

    public Optional<ShipmentResponse> getShipment(String shipmentId) {
        return Optional.ofNullable(shipmentRepository.get(shipmentId));
    }
}
