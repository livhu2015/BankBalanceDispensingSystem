package com.discoverybank.balancedispense.model.dao;

import java.math.BigDecimal;

public class Denomination {

    private Integer denomination_id;
    private BigDecimal value;
    private String denomination_type_code;

    public Integer getDenomination_id() {
        return denomination_id;
    }

    public void setDenomination_id(Integer denomination_id) {
        this.denomination_id = denomination_id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDenomination_type_code() {
        return denomination_type_code;
    }

    public void setDenomination_type_code(String denomination_type_code) {
        this.denomination_type_code = denomination_type_code;
    }
}