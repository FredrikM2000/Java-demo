package com.example.demo;

import java.time.Instant;

public class Vote {

    Instant publishedAt;
    Poll poll;
    User voter;
    VoteOption option;

    //Constructor
    public Vote() {
    }

    //Getters
    public Instant getPublishedAt() {
        return publishedAt;
    }

    public User getVoter() {
        return voter;
    }
    public VoteOption getOption() {
        return option;
    }
    public Poll getPoll() {return poll;}

    //Setters
    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }
    public void setVoter(User voter) {this.voter = voter;}
    public void setOption(VoteOption option) {this.option = option;}
    public void setPoll(Poll poll) {this.poll = poll;}
}