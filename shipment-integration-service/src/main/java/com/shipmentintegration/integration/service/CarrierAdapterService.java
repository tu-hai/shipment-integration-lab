package com.shipmentintegration.integration.service;

import com.shipmentintegration.integration.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class CarrierAdapterService {

    private static final Logger log = LoggerFactory.getLogger(CarrierAdapterService.class);
    private final RestTemplate restTemplate;

    @Value("${app.carrier-mock.url:http://localhost:8003/api/v1/carrier}")
    private String carrierMockUrl;

    public CarrierAdapterService() {
        this.restTemplate = new RestTemplate();
    }

    public CarrierQuoteResponse fetchQuote(String destinationCity, String destinationCountry) {
        log.info("Requesting carrier quote for city={}, country={}", destinationCity, destinationCountry);
        try {
            CarrierQuoteRequest request = new CarrierQuoteRequest("Hanoi", destinationCity, destinationCountry, 2.5);
            CarrierQuoteResponse response = restTemplate.postForObject(carrierMockUrl + "/quotes", request, CarrierQuoteResponse.class);
            if (response != null) {
                return response;
            }
        } catch (Exception e) {
            log.warn("Carrier quote request failed via REST API: {}. Using internal fallback mock quote.", e.getMessage());
        }
        return new CarrierQuoteResponse("Q-FALLBACK-" + UUID.randomUUID().toString().substring(0, 4), "FAST_SHIP_EXPRESS", 15.50, "USD", 2);
    }

    public CarrierBookingResponse bookShipment(String quoteId, String orderId) {
        log.info("Booking shipment with carrier for quoteId={}, orderId={}", quoteId, orderId);
        try {
            CarrierBookingRequest request = new CarrierBookingRequest(quoteId, orderId);
            CarrierBookingResponse response = restTemplate.postForObject(carrierMockUrl + "/shipments", request, CarrierBookingResponse.class);
            if (response != null) {
                return response;
            }
        } catch (Exception e) {
            log.warn("Carrier booking failed via REST API: {}. Using internal fallback booking.", e.getMessage());
        }
        return new CarrierBookingResponse("BK-FALLBACK-" + UUID.randomUUID().toString().substring(0, 6), "TRK-VN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(), "CONFIRMED");
    }
}
