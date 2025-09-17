package com.example.demo;

public class User {
    int id;
    String username;
    String email;

    public User (){}
    public User (String username, String email)
    {
        this.username = username;
        this.email = email;

    }

    public Poll createPoll(String question)
    {
        Poll poll = new Poll();
        poll.setQuestion(question);
        return poll;
    }


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