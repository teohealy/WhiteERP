package com.example.entities;

import java.util.Date;
import java.sql.Time;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private Date dateBirth;
    private String number;

    public Client(String firstName, String lastName, Date dateBirth, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.number = number;
    }

    public Client(){}

    public int getClientId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
