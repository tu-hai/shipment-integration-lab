package com.shipmentintegration.order.dto;

public class OrderResponse {
    private String orderId;
    private String status;
    private String customerId;
    private String createdAt;

    public OrderResponse() {}

    public OrderResponse(String orderId, String status, String customerId, String createdAt) {
        this.orderId = orderId;
        this.status = status;
        this.customerId = customerId;
        this.createdAt = createdAt;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
