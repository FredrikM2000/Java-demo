package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//    }
//    @GetMapping()
//    public String hello() {
//        return "Hello World!";

        // Create poll manager
        PollManager manager = new PollManager();

        // Create user
        User user1 = new User();
        user1.setUsername("Alice");
        user1.setEmail("alice@example.com");
        manager.addUser(user1);

        // Print out users
        System.out.println("Users:");
        manager.getUsers().forEach((k) -> System.out.println(k.getUsername() + ", " + k.getEmail()));

        // Create user
        User user2 = new User();
        user2.setUsername("Fredrik");
        user2.setEmail("fredrik@example.com");
        manager.addUser(user2);

        // Print out users
        System.out.println("Users:");
        manager.getUsers().forEach((k) -> System.out.println(k.getUsername() + ", " + k.getEmail()));

        // Create a poll
        Poll poll1 = new Poll();
        poll1.setQuestion("What's your favorite color?");
        poll1.setOwner(user1);
        poll1.setPublishedAt(Instant.now());
        poll1.setValidUntil(Instant.now().plusSeconds(3600));
        manager.addPoll(poll1);

        // Print out polls
        System.out.println("Polls:");
        manager.getPolls().forEach((p) -> System.out.println(p.getQuestion() + " valid until: " + p.getValidUntil()));

        // Create vote
        Vote vote1 = new Vote();
        vote1.setPoll(poll1);
        vote1.setVoter(user2);
        // Create vote option
        VoteOption option1 = new VoteOption();
        option1.setCaption("Caption");
        vote1.setOption(option1);
        manager.addVote(vote1);

        // Print out votes
        System.out.println("Votes:");
        manager.getVotes().forEach((v) -> System.out.println(
                v.getPoll().question + ", " +
                v.voter.getUsername() + ", " +
                v.getOption().getCaption()));

        // Remove poll1
        manager.removePoll(poll1);

        // Print out votes
        System.out.println("Votes:");
        manager.getVotes().forEach((v) -> System.out.println(
                v.getPoll().question + ", " +
                        v.voter.getUsername() + ", " +
                        v.getOption().getCaption()));
    }
}
