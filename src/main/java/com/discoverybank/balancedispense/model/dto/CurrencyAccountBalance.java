package com.discoverybank.balancedispense.model.dto;

import java.io.Serializable;

public class CurrencyAccountBalance implements Serializable {
    private String accountNumber;
    private String currencyCode;
    private double conversionRate;
    private double currencyBalance;
    private double randAmount;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getRandAmount() {
        return randAmount;
    }

    public void setRandAmount(double randAmount) {
        this.randAmount = randAmount;
    }

    public double getCurrencyBalance() {
        return currencyBalance;
    }

    public void setCurrencyBalance(double currencyBalance) {
        this.currencyBalance = currencyBalance;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
}
