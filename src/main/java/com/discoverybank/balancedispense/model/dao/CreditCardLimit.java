package com.discoverybank.balancedispense.model.dao;

import java.math.BigDecimal;

public class CreditCardLimit {
    private String client_account_number;
    private BigDecimal account_limit;

    public String getClient_account_number() {
        return client_account_number;
    }

    public void setClient_account_number(String client_account_number) {
        this.client_account_number = client_account_number;
    }

    public BigDecimal getAccount_limit() {
        return account_limit;
    }

    public void setAccount_limit(BigDecimal account_limit) {
        this.account_limit = account_limit;
    }
    
}