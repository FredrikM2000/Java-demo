package com.example.demo;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "polls")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String question;
    Instant publishedAt;
    Instant validUntil;

    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    User createdBy;

    @OneToMany(mappedBy="poll", cascade = CascadeType.ALL, orphanRemoval = true)
    List<VoteOption> options = new ArrayList<>();

    //Constructor
    public Poll () {
        publishedAt = Instant.now();
    }

    public void addVoteOption(VoteOption option) {
        option.setPoll(this);
        options.add(option);
    }
    public VoteOption addVoteOption(String option) {
        VoteOption voteOption = new VoteOption();
        voteOption.setCaption(option);
        voteOption.setPoll(this);
        voteOption.setPresentationOrder(options.size());
        options.add(voteOption);

        return voteOption;
    }
    public List<VoteOption> getOptions() {return options;}

    //Getters
    public Long getId() {return id;}
    public String getQuestion() {return question;}
    public Instant getPublishedAt(){return publishedAt;}
    public Instant getValidUntil() {return validUntil;}
    public User getCreatedBy() {return createdBy;}

    //Setters
    public void setId(Long id) {this.id = id;}
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setPublishedAt(Instant publishedAt){
        this.publishedAt = publishedAt;
    }
    public void setValidUntil (Instant validUntil) { this.validUntil = validUntil; }
    public void setCreatedBy(User createdBy) {this.createdBy = createdBy;}
}