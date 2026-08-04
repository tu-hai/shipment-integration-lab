package com.shipmentintegration.carrier.dto;

public class QuoteResponse {
    private String quoteId;
    private String carrierName;
    private double estimatedCost;
    private String currency;
    private int estimatedDays;

    public QuoteResponse() {}

    public QuoteResponse(String quoteId, String carrierName, double estimatedCost, String currency, int estimatedDays) {
        this.quoteId = quoteId;
        this.carrierName = carrierName;
        this.estimatedCost = estimatedCost;
        this.currency = currency;
        this.estimatedDays = estimatedDays;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getEstimatedDays() {
        return estimatedDays;
    }

    public void setEstimatedDays(int estimatedDays) {
        this.estimatedDays = estimatedDays;
    }
}
