package com.shipmentintegration.integration.controller;

import com.shipmentintegration.integration.dto.ShipmentRequest;
import com.shipmentintegration.integration.dto.ShipmentResponse;
import com.shipmentintegration.integration.service.ShipmentOrchestrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final ShipmentOrchestrationService orchestrationService;

    public ShipmentController(ShipmentOrchestrationService orchestrationService) {
        this.orchestrationService = orchestrationService;
    }

    @PostMapping
    public ResponseEntity<ShipmentResponse> createShipment(@RequestBody ShipmentRequest request) {
        ShipmentResponse response = orchestrationService.processShipment(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<ShipmentResponse> getShipment(@PathVariable String shipmentId) {
        return orchestrationService.getShipment(shipmentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{shipmentId}/retry")
    public ResponseEntity<ShipmentResponse> retryShipment(@PathVariable String shipmentId) {
        ShipmentResponse response = orchestrationService.retryShipment(shipmentId);
        return ResponseEntity.ok(response);
    }
}
