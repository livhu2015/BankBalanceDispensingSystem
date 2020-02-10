package com.discoverybank.balancedispense.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionalAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private String accountName;
    private String accountNumber;
    private String currencyCode;
    private double displayBalance;
    private boolean transactional;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(double displayBalance) {
        this.displayBalance = displayBalance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public boolean isTransactional() {
        return transactional;
    }

    public void setTransactional(boolean transactional) {
        this.transactional = transactional;
    }
}
