package com.discoveybank.balancedispensing.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ClientAggregate {

    private String title;
    private String name;
    private String surname;

    private BigDecimal loanBalance;
    private BigDecimal transactionalBalance;
    private BigDecimal netPosition;

    public BigDecimal getNetPosition() {
        return netPosition;
    }

    public void setNetPosition(BigDecimal netPosition) {
        this.netPosition = netPosition;
    }



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

    public BigDecimal getTransactionalBalance() {
        return transactionalBalance;
    }

    public void setTransactionalBalance(BigDecimal transactionalBalance) {
        this.transactionalBalance = transactionalBalance;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(BigDecimal loanBalance) {
        this.loanBalance = loanBalance;
    }
}
