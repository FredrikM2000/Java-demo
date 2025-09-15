package com.example.demo.Controllers;

import com.example.demo.PollManager;
import com.example.demo.Poll;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/polls")
public class PollController {

    final PollManager manager;

    public PollController(PollManager manager) {this.manager = manager;}

    @GetMapping
    public Collection<Poll> allPolls() {
        return manager.getPolls();
    }

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        manager.addPoll(poll);
        return poll;
    }
    @DeleteMapping("/{id}")
    public void deletePoll(@PathVariable int  id) {
        manager.removePoll(id);
    }
}
