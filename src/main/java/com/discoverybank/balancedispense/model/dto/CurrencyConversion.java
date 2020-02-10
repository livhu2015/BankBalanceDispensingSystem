package com.discoverybank.balancedispense.model.dto;

public class CurrencyConversion {
    private double conversionRate;
    private String currencyCode;

    public double getRate() {
        return conversionRate;
    }

    public void setRate(double rate) {
        this.conversionRate = rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
