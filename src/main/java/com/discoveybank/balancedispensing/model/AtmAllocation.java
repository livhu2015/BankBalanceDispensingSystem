package com.discoveybank.balancedispensing.model;

public class AtmAllocation {
    private Integer atm_allocation_id;
    private Integer atm_id;
    private Integer denomination_id;
    private Integer count;

    public Integer getAtm_allocation_id() {
        return atm_allocation_id;
    }

    public void setAtm_allocation_id(Integer atm_allocation_id) {
        this.atm_allocation_id = atm_allocation_id;
    }

    public Integer getAtm_id() {
        return atm_id;
    }

    public void setAtm_id(Integer atm_id) {
        this.atm_id = atm_id;
    }

    public Integer getDenomination_id() {
        return denomination_id;
    }

    public void setDenomination_id(Integer denomination_id) {
        this.denomination_id = denomination_id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}