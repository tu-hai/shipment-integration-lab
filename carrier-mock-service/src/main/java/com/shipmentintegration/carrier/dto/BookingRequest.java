package com.shipmentintegration.carrier.dto;

public class BookingRequest {
    private String quoteId;
    private String orderId;

    public BookingRequest() {}

    public BookingRequest(String quoteId, String orderId) {
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
