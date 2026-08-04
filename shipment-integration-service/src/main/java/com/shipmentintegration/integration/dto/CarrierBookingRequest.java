package com.shipmentintegration.integration.dto;

public class CarrierBookingRequest {
    private String quoteId;
    private String orderId;

    public CarrierBookingRequest() {}

    public CarrierBookingRequest(String quoteId, String orderId) {
        this.quoteId = quoteId;
        this.orderId = orderId;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
