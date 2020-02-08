package com.discoveybank.balancedispensing.model;

import java.time.LocalDate;

public class Client {

    private Integer client_id;
    private String title;
    private String name;
    private String surname;
    private LocalDate dob;
    private String client_sub_type_code;

    public Client(String title, String name, String surname) {
        this.title = title;
        this.name = name;
        this.surname = surname;
    }

    public Client(Integer client_id, String title, String name, String surname, LocalDate dob, String client_sub_type_code) {

        this.client_id = client_id;
        this.title = title;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.client_sub_type_code = client_sub_type_code;
    }

    public Integer getClientId() {
        return client_id;
    }

    public String getClientSubTypeCode() {
        return client_sub_type_code;
    }

    public void setClientSubTypeCode(String clientSubTypeCode) {
        this.client_sub_type_code = clientSubTypeCode;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClientId(Integer client_id) {
        this.client_id = client_id;
    }

}