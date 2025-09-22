package com.example.demo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "voteOptions")
public class VoteOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    @ManyToOne
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    @OneToMany(mappedBy = "votesOn", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Vote> votes = new ArrayList<>();


    String caption;
    int presentationOrder;
    int votesCount;

    public VoteOption() { }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public List<Vote> getVotes() {return votes;}
    public void setVotes(List<Vote> votes) {this.votes = votes;}

    //Getters
    public String getCaption() {
        return caption;
    }
    public int getPresentationOrder(){
        return presentationOrder;
    }
    public int getVotesCount(){return votesCount;}

    //Setters
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public void setPresentationOrder(int presentationOrder)
    {
        this.presentationOrder = presentationOrder;
    }
    public void setVotesCount(int votesCount) {this.votesCount = votesCount;}
}