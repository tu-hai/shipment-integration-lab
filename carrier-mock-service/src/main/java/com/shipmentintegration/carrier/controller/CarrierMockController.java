package com.shipmentintegration.carrier.controller;

import com.shipmentintegration.carrier.dto.*;
import com.shipmentintegration.carrier.service.CarrierMockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carrier")
public class CarrierMockController {

    private final CarrierMockService carrierMockService;

    public CarrierMockController(CarrierMockService carrierMockService) {
        this.carrierMockService = carrierMockService;
    }

    @PostMapping("/quotes")
    public ResponseEntity<QuoteResponse> getQuote(@RequestBody QuoteRequest request) {
        QuoteResponse response = carrierMockService.generateQuote(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/shipments")
    public ResponseEntity<BookingResponse> createShipment(@RequestBody BookingRequest request) {
        BookingResponse response = carrierMockService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/shipments/{carrierShipmentId}")
    public ResponseEntity<CarrierTrackingResponse> getTracking(@PathVariable String carrierShipmentId) {
        return carrierMockService.getTrackingInfo(carrierShipmentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
