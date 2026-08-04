package com.shipmentintegration.order.event;

import java.io.Serializable;

public class OrderCreatedEvent implements Serializable {
    private String orderId;
    private String customerId;
    private String destinationCountry;
    private String destinationCity;
    private String timestamp;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(String orderId, String customerId, String destinationCountry, String destinationCity, String timestamp) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.destinationCountry = destinationCountry;
        this.destinationCity = destinationCity;
        this.timestamp = timestamp;
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

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
