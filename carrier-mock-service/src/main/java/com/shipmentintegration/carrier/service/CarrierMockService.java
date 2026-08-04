package com.shipmentintegration.carrier.service;

import com.shipmentintegration.carrier.dto.*;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CarrierMockService {

    private static final Logger log = LoggerFactory.getLogger(CarrierMockService.class);
    private final Map<String, CarrierTrackingResponse> bookingStore = new ConcurrentHashMap<>();
    private final Counter quoteCounter;
    private final Counter bookingCounter;

    public CarrierMockService(MeterRegistry meterRegistry) {
        this.quoteCounter = Counter.builder("carrier_mock_quotes_total")
                .description("Total carrier mock quotes requested")
                .register(meterRegistry);
        this.bookingCounter = Counter.builder("carrier_mock_bookings_total")
                .description("Total carrier mock bookings requested")
                .register(meterRegistry);
    }

    public QuoteResponse generateQuote(QuoteRequest request) {
        quoteCounter.increment();
        String quoteId = "Q-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        log.info("Carrier Partner Mock: Generating quote for destination={}, city={}", request.getDestinationCountry(), request.getDestinationCity());
        return new QuoteResponse(quoteId, "FAST_SHIP_EXPRESS", 19.99, "USD", 3);
    }

    public BookingResponse createBooking(BookingRequest request) {
        bookingCounter.increment();
        String bookingId = "CARRIER-BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String trackingNo = "TRK-VN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        log.info("Carrier Partner Mock: Creating booking for orderId={}, quoteId={}", request.getOrderId(), request.getQuoteId());
        CarrierTrackingResponse tracking = new CarrierTrackingResponse(
                bookingId,
                trackingNo,
                "IN_TRANSIT",
                "Hanoi Logistics Hub",
                Instant.now().plus(3, ChronoUnit.DAYS).toString()
        );
        bookingStore.put(bookingId, tracking);

        return new BookingResponse(bookingId, trackingNo, "CONFIRMED");
    }

    public Optional<CarrierTrackingResponse> getTrackingInfo(String carrierShipmentId) {
        return Optional.ofNullable(bookingStore.get(carrierShipmentId));
    }
}
