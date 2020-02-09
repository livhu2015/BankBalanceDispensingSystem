package com.discoverybank.balancedispense.model.dao;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClientAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    private String account_number;
    private Integer client_id;
    private String account_type_code;
    private String currency_code;
    private BigDecimal display_balance;

    public ClientAccount(){}

    public ClientAccount(String account_number, Integer client_id, String account_type_code, String currency_code,
            BigDecimal display_balance) {
        this.account_number = account_number;
        this.client_id = client_id;
        this.account_type_code = account_type_code;
        this.currency_code = currency_code;
        this.display_balance = display_balance;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getAccount_type_code() {
        return account_type_code;
    }

    public void setAccount_type_code(String account_type_code) {
        this.account_type_code = account_type_code;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public BigDecimal getDisplay_balance() {
        return display_balance;
    }

    public void setDisplay_balance(BigDecimal display_balance) {
        this.display_balance = display_balance;
    }

    


}