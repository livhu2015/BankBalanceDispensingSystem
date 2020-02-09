package com.discoverybank.balancedispense.model.dao;

public class AccountType {
    private String account_type_code;
    private String description;
    private Boolean transactional;

    public String getAccount_type_code() {
        return account_type_code;
    }

    public Boolean getTransactional() {
        return transactional;
    }

    public void setTransactional(Boolean transactional) {
        this.transactional = transactional;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAccount_type_code(String account_type_code) {
        this.account_type_code = account_type_code;
    }
}