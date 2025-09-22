package com.example.demo;

import jakarta.persistence.*;

import java.util.LinkedHashSet;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String email;
    LinkedHashSet<Poll> createdPolls;

    public User (){}
    public User (String username, String email)
    {
        this.username = username;
        this.email = email;
        this.createdPolls = new LinkedHashSet<Poll>();
    }

    public Poll createPoll(String question)
    {
        Poll poll = new Poll();
        poll.setQuestion(question);
        poll.createdBy = this;
        return poll;
    }

    public Vote voteFor(VoteOption voteOption)
    {
        Vote vote = new Vote();
        vote.setVotesOn(voteOption);
        vote.setPoll(voteOption.getPoll());
        vote.voter = this;
        voteOption.setVotesCount(voteOption.getVotesCount() + 1);
        voteOption.getVotes().add(vote);
        return vote;
    }

    // Getters
    public long getId() {return id;}
    public String getUsername() { return username; }
    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(long id) {this.id = id;}
    public void setEmail(String email) {
        this.email = email;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}