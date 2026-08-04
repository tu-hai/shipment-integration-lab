package com.shipmentintegration.carrier.dto;

public class BookingResponse {
    private String carrierBookingId;
    private String trackingNumber;
    private String status;

    public BookingResponse() {}

    public BookingResponse(String carrierBookingId, String trackingNumber, String status) {
        this.carrierBookingId = carrierBookingId;
        this.trackingNumber = trackingNumber;
        this.status = status;
    }

    public String getCarrierBookingId() {
        return carrierBookingId;
    }

    public void setCarrierBookingId(String carrierBookingId) {
        this.carrierBookingId = carrierBookingId;
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
}
