package com.example.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private String firstName;
    private String lastName;

    private Post post = new Post();
    private String username;
    private String password;
    private String number;
    private String gender;

    public User(String firstName, String lastName, Post post, String username, String password, String number, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.post = new Post();
        this.username = username;
        this.password = password;
        this.number = number;
        this.gender = gender;
    }

    public User(){}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Post getPost() {
        return post;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNumber() {
        return number;
    }

    public String getGender() {
        return gender;
    }
}

