package com.example.demo;

public class User {

    String username;
    String email;

    public User (){System.out.println("User created");}

    public String getUsername() { return username; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}