package com.shipmentintegration.integration.dto;

public class ShipmentResponse {
    private String shipmentId;
    private String orderId;
    private String status;
    private String carrierName;
    private String trackingNumber;
    private String createdAt;

    public ShipmentResponse() {}

    public ShipmentResponse(String shipmentId, String orderId, String status, String carrierName, String trackingNumber, String createdAt) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.status = status;
        this.carrierName = carrierName;
        this.trackingNumber = trackingNumber;
        this.createdAt = createdAt;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
