package com.shipmentintegration.carrier.dto;

public class CarrierTrackingResponse {
    private String carrierShipmentId;
    private String trackingNumber;
    private String status;
    private String currentLocation;
    private String estimatedDelivery;

    public CarrierTrackingResponse() {}

    public CarrierTrackingResponse(String carrierShipmentId, String trackingNumber, String status, String currentLocation, String estimatedDelivery) {
        this.carrierShipmentId = carrierShipmentId;
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.currentLocation = currentLocation;
        this.estimatedDelivery = estimatedDelivery;
    }

    public String getCarrierShipmentId() {
        return carrierShipmentId;
    }

    public void setCarrierShipmentId(String carrierShipmentId) {
        this.carrierShipmentId = carrierShipmentId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public void setEstimatedDelivery(String estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }
}
