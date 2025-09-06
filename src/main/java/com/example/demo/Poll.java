package com.example.demo;

import java.time.Instant;

public class Poll {

    String question;
    Instant publishedAt;
    Instant validUntil;

    User owner;

    //Constructor
    public Poll () {System.out.println("Poll created");}

    //Getters
    public String getQuestion() {
        return question;
    }
    public Instant getPublishedAt(){
        return publishedAt;
    }
    public Instant getValidUntil() {
        return validUntil;
    }
    public User getOwner() {return owner;}

    //Setters
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setPublishedAt(Instant publishedAt){
        this.publishedAt = publishedAt;
    }
    public void setValidUntil (Instant validUntil) { this.validUntil = validUntil; }
    public void setOwner(User owner) {this.owner = owner;}
}