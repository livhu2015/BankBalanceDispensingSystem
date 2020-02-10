package com.discoverybank.balancedispense.model.dto;

public class CurrencyConversion {
    private double rate;
    private String code;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
