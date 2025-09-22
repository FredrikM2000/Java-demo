package com.example.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class PollManager {

    Map<Long, Poll> polls = new HashMap<>();
    Map<Long, User> users = new HashMap<>();
    Map<Long, Vote> votes = new HashMap<>();
    Long nextPollId = 0L;
    Long nextUserId = 0L;
    Long nextVoteId = 0L;

    public PollManager (){}

    // Add
    public void addPoll(Poll poll) {
        poll.setId(nextPollId);
        polls.put(nextPollId++, poll);
        poll.setCreatedBy(users.get(poll.getCreatedBy().getId()));
    }
    public void addUser(User user) {
        user.setId(nextUserId);
        users.put(nextUserId++, user);
    }
    public void addVote(Vote vote) {
        vote.setId(nextVoteId);
        votes.put(nextVoteId++, vote);
    }

    // Get
    public Poll getPoll(long id) {
        return polls.get(id);
    }
    public User getUser(long id) { return users.get(id); }
    public Vote getVote(long id) { return votes.get(id); }

    // Get all
    public Collection<Poll> getPolls() {return polls.values();}
    public Collection<User> getUsers() {return users.values();}
    public Collection<Vote> getVotes() {return votes.values();}

    // Remove
    public void removePoll (long pollId) {
        Poll pollToDelete = polls.get(pollId);
        if(pollToDelete != null){
            votes.values().removeIf(vote -> vote.getPoll().getId() == pollId);
            polls.remove(pollId);
        }
    }

    // Size
    public int getPollCount() { return polls.size(); }
    public int getUserCount() {
        return users.size();
    }
    public int getVoteCount() {return votes.size();}

}