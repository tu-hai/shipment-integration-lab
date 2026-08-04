package com.shipmentintegration.order.dto;

import java.util.List;

public class OrderRequest {
    private String orderId;
    private String customerId;
    private Destination destination;
    private List<OrderItem> items;

    public OrderRequest() {}

    public OrderRequest(String orderId, String customerId, Destination destination, List<OrderItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.destination = destination;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
