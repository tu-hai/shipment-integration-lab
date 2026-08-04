package com.shipmentintegration.carrier.dto;

public class QuoteRequest {
    private String originCity;
    private String destinationCity;
    private String destinationCountry;
    private double weightKg;

    public QuoteRequest() {}

    public QuoteRequest(String originCity, String destinationCity, String destinationCountry, double weightKg) {
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
