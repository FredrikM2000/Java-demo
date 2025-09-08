package com.example.demo.Controllers;

import com.example.demo.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Collection;

@RestController
@RequestMapping("/votes")
public class VoteController {

    final PollManager manager;

    public VoteController(PollManager manager) {this.manager = manager;}

    @GetMapping
    public Collection<Vote> allVotes() {
        return manager.getVotes();
    }

    @PostMapping
    public Vote createVote(@RequestBody Vote vote) {
        Poll poll = manager.getPoll(vote.getPoll().getId());
        User voter = manager.getUser(vote.getVoter().getId());

        VoteOption chosenOption = poll.getOptions().stream()
                .filter(opt -> opt.getCaption().equals(vote.getOption().getCaption()))
                .findFirst()
                .orElse(null);

        vote.setPoll(poll);
        vote.setVoter(voter);
        vote.setOption(chosenOption);

        manager.addVote(vote);
        return vote;
    }

    @PutMapping("/{voteId}")
    public Vote updateVote(@PathVariable int voteId, @RequestBody Vote voteUpdate) {
        Vote existingVote = manager.getVote(voteId);
        if (existingVote == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vote not found");
        }

        // Update option
        Poll realPoll = manager.getPoll(existingVote.getPoll().getId());
        VoteOption chosenOption = realPoll.getOptions().stream()
                .filter(opt -> opt.getCaption().equals(voteUpdate.getOption().getCaption()))
                .findFirst()
                .orElse(null);

        existingVote.setOption(chosenOption);
        existingVote.setPublishedAt(Instant.now());

        return existingVote;
    }


    @DeleteMapping("/{id}")
    public void deleteVote(@PathVariable int id) {
        if (manager.getVote(id) != null) {
            manager.getVotes().removeIf(vote -> vote.getId() == id);
        }
    }
}
