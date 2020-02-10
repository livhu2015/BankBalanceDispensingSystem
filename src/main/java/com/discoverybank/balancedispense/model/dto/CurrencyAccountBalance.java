package com.discoverybank.balancedispense.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CurrencyAccountBalance implements Serializable {
    private String accountNumber;
    private String currencyCode;
    private double ConversionRate;
    private double displayBalance;
    private double convertedAmount;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getConversionRate() {
        return ConversionRate;
    }

    public void setConversionRate(double conversionRate) {
        ConversionRate = conversionRate;
    }

    public double getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(double displayBalance) {
        this.displayBalance = displayBalance;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
