package com.example.demo;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    Poll poll;

    @ManyToOne
    @JoinColumn(name = "voter_id")
    User voter;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    VoteOption votesOn;

    Instant publishedAt;

    //Constructor
    public Vote() {
        publishedAt = Instant.now();
    }

    //Getters
    public Long getId() {return id;}
    public Poll getPoll() {return poll;}
    public User getVoter() {return voter;}
    public VoteOption getVotesOn() {return votesOn;}
    public Instant getPublishedAt() {return publishedAt;}

    //Setters
    public void setId(Long id) {this.id = id;}
    public void setPoll(Poll poll) {this.poll = poll;}
    public void setVoter(User voter) {this.voter = voter;}
    public void setVotesOn(VoteOption votesOn) {this.votesOn = votesOn;}
    public void setPublishedAt(Instant publishedAt) {this.publishedAt = publishedAt;}
}