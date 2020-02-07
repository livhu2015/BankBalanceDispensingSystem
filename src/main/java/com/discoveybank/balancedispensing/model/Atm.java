package com.discoveybank.balancedispensing.model;

public class Atm {
    private Integer atm_id;
    private String name;
    private String location;

    public Integer getAtm_id() {
        return atm_id;
    }

    public void setAtm_id(Integer atm_id) {
        this.atm_id = atm_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}