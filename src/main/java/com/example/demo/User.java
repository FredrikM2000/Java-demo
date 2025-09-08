package com.example.demo;

public class User {
    int id;
    String username;
    String email;

    public User (){}

    // Getters
    public int getId() {return id;}
    public String getUsername() { return username; }
    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(int id) {this.id = id;}
    public void setEmail(String email) {
        this.email = email;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}