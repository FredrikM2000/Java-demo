package com.example.demo;

import java.time.Instant;

public class Vote {
    int id;
    Poll poll;
    User voter;
    VoteOption option;
    Instant publishedAt;

    //Constructor
    public Vote() {
        publishedAt = Instant.now();
    }

    //Getters
    public int getId() {return id;}
    public Poll getPoll() {return poll;}
    public User getVoter() {return voter;}
    public VoteOption getOption() {return option;}
    public Instant getPublishedAt() {return publishedAt;}

    //Setters
    public void setId(int id) {this.id = id;}
    public void setPoll(Poll poll) {this.poll = poll;}
    public void setVoter(User voter) {this.voter = voter;}
    public void setOption(VoteOption option) {this.option = option;}
    public void setPublishedAt(Instant publishedAt) {this.publishedAt = publishedAt;}
}