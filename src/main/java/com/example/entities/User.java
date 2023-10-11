package com.example.entities;

public class User {
    private String firstName;
    private String lastName;

    private String username;
    private String password;
    private String number;
    private String gender;
    private Post post = new Post();

    public User(String firstName, String lastName, String username, String password, String number, String gender, Post post) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.number = number;
        this.gender = gender;
        this.post = post;
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

