package com.discoveybank.balancedispensing.model;

import java.math.BigDecimal;

public class ClientAccountDetail {

    private String title, name, surname, account_number, description;
    private BigDecimal display_balance;
    private Boolean transactional;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDisplay_balance() {
        return display_balance;
    }

    public void setDisplay_balance(BigDecimal display_balance) {
        this.display_balance = display_balance;
    }

    public Boolean getTransactional() {
        return transactional;
    }

    public void setTransactional(Boolean transactional) {
        this.transactional = transactional;
    }
}
