package com.discoveybank.balancedispensing.model;

import java.math.BigDecimal;

public class CurrencyConversionRate {

    private String currency_code;
    private String conversion_indicator;
    private BigDecimal rate;

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getConversion_indicator() {
        return conversion_indicator;
    }

    public void setConversion_indicator(String conversion_indicator) {
        this.conversion_indicator = conversion_indicator;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    
}