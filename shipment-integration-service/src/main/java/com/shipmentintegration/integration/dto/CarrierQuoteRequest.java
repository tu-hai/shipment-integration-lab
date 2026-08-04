package com.shipmentintegration.integration.dto;

public class CarrierQuoteRequest {
    private String originCity;
    private String destinationCity;
    private String destinationCountry;
    private double weightKg;

    public CarrierQuoteRequest() {}

    public CarrierQuoteRequest(String originCity, String destinationCity, String destinationCountry, double weightKg) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.destinationCountry = destinationCountry;
        this.weightKg = weightKg;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }
}
