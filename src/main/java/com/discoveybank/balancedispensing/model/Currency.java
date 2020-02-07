package com.discoveybank.balancedispensing.model;

public class Currency {

    private String currency_code;
    private Integer decimal_places;
    private String description;

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public Integer getDecimal_places() {
        return decimal_places;
    }

    public void setDecimal_places(Integer decimal_places) {
        this.decimal_places = decimal_places;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}