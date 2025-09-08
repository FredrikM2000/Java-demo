package com.example.demo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Poll {
    int id;
    String question;
    Instant publishedAt;
    Instant validUntil;
    User owner;
    List<VoteOption> options = new ArrayList<>();

    //Constructor
    public Poll () {
        publishedAt = Instant.now();
    }

    public void addOption(VoteOption option) {options.add(option);}
    public List<VoteOption> getOptions() {return options;}

    //Getters
    public int getId() {return id;}
    public String getQuestion() {return question;}
    public Instant getPublishedAt(){return publishedAt;}
    public Instant getValidUntil() {return validUntil;}
    public User getOwner() {return owner;}

    //Setters
    public void setId(int id) {this.id = id;}
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setPublishedAt(Instant publishedAt){
        this.publishedAt = publishedAt;
    }
    public void setValidUntil (Instant validUntil) { this.validUntil = validUntil; }
    public void setOwner(User owner) {this.owner = owner;}
}