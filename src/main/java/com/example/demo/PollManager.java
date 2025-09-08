package com.example.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.example.demo.User;
import com.example.demo.Poll;

@Component
public class PollManager {

    Map<Integer, Poll> polls = new HashMap<>();
    Map<Integer, User> users = new HashMap<>();
    Map<Integer, Vote> votes = new HashMap<>();
    int nextPollId = 1;
    int nextUserId = 1;
    int nextVoteId = 1;

    public PollManager (){}

    // Add
    public void addPoll(Poll poll) {
        poll.setId(nextPollId);
        polls.put(nextPollId++, poll);
        poll.setOwner(users.get(poll.getOwner().getId()));
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
    public Poll getPoll(int id) {
        return polls.get(id);
    }
    public User getUser(int id) { return users.get(id); }
    public Vote getVote(int id) { return votes.get(id); }

    // Get all
    public Collection<Poll> getPolls() {return polls.values();}
    public Collection<User> getUsers() {return users.values();}
    public Collection<Vote> getVotes() {return votes.values();}

    // Remove
    public void removePoll (int pollId) {
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