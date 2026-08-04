package com.shipmentintegration.integration.dto;

public class ShipmentRequest {
    private String orderId;
    private String carrierPreference;

    public ShipmentRequest() {}

    public ShipmentRequest(String orderId, String carrierPreference) {
        this.orderId = orderId;
        this.carrierPreference = carrierPreference;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCarrierPreference() {
        return carrierPreference;
    }

    public void setCarrierPreference(String carrierPreference) {
        this.carrierPreference = carrierPreference;
    }
}
