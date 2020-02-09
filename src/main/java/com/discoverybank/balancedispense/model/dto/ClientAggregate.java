package com.discoverybank.balancedispense.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClientAggregate implements Serializable {

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

    public static class ClientAccountSummary {
        private String name, surname, accountNumber, description;
        private BigDecimal displayBalance;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public BigDecimal getDisplayBalance() {
            return displayBalance;
        }

        public void setDisplayBalance(BigDecimal displayBalance) {
            this.displayBalance = displayBalance;
        }
    }
}
