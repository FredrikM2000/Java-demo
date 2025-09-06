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
    Map<String, Vote> votes = new HashMap<>();
    int nextPollId = 1;
    int nextUserId = 1;

    public PollManager (){System.out.println("Poll manager created");}

    public void addPoll(Poll poll) {
        polls.put(nextPollId++, poll);
        System.out.println("Poll added");
    }
    public Poll getPoll(int id) {
        return polls.get(id);
    }
    public Collection<Poll> getPolls() {return polls.values();}
    public void removePoll (Poll pollToDelete) {
        System.out.println("Poll: " + pollToDelete.getQuestion() + " removed");
        votes.values().removeIf(vote -> vote.getPoll().equals(pollToDelete));
        polls.values().removeIf(poll -> poll.equals(pollToDelete));
    }
    public int getPollCount() {
        return polls.size();
    }

    public void addUser(User user) {
        users.put(nextUserId++, user);
        System.out.println("User added");
    }
    public User getUser(int id) {
        return users.get(id);
    }
    public Collection<User> getUsers() {return users.values();}
    public int getUserCount() {
        return users.size();
    }

    public void addVote(Vote vote) {
        votes.put(vote.getVoter().username + "_" + vote.getPoll().question, vote);
    }
    public Collection<Vote> getVotes() {return votes.values();}
}