//package com.example.demo;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.web.client.RestClient;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class DemoApplicationTests {
//
//    final RestClient restClient = RestClient.create();
//
//	@Test
//	void testUserCrud() {
//        String baseUrl = "http://localhost:8080";
//
//        // Create user
//	    User fredrik = new User();
//        fredrik.setUsername("fredrik");
//        fredrik.setEmail("fredrik@example.com");
//        User createdUser1 = restClient.post().uri(baseUrl + "/users").body(fredrik).retrieve().body(User.class);
//        assert createdUser1 != null;
//        assertEquals("fredrik", createdUser1.getUsername());
//        System.out.println("User " +  createdUser1.getUsername() + " created");
//
//        //List all users
//        User[] users = restClient.get().uri(baseUrl + "/users").retrieve().body(User[].class);
//        assert users != null;
//        System.out.println("Users:");
//        for (User u : users) {
//            System.out.println(" - " + u.getUsername() + " (" + u.getEmail() + ")");
//        }
//        assertEquals(1, users.length);
//
//        // Create another user
//        User ola = new User();
//        ola.setUsername("ola");
//        ola.setEmail("ola@example.com");
//        User createdUser2 = restClient.post().uri(baseUrl + "/users").body(ola).retrieve().body(User.class);
//        assert createdUser2 != null;
//        assertEquals("ola", createdUser2.getUsername());
//        System.out.println("User " +  createdUser2.getUsername() + " created");
//
//        //List all users
//        users = restClient.get().uri(baseUrl + "/users").retrieve().body(User[].class);
//        assert users != null;
//        System.out.println("Users:");
//        for (User u : users) {
//            System.out.println(" - " + u.getUsername() + " (" + u.getEmail() + ")");
//        }
//        assertEquals(2, users.length);
//
//        // Create poll
//        Poll poll = new Poll();
//        poll.setQuestion("Do you like coffee?");
//        poll.setCreatedBy(createdUser1);
//
//        // Add options to poll
//        VoteOption yesOption = new VoteOption();
//        yesOption.setCaption("Yes");
//        yesOption.setPresentationOrder(0);
//        poll.getOptions().add(yesOption);
//
//        VoteOption noOption = new VoteOption();
//        noOption.setCaption("No");
//        noOption.setPresentationOrder(1);
//        poll.getOptions().add(noOption);
//
//        Poll createdPoll = restClient.post().uri(baseUrl + "/polls").body(poll).retrieve().body(Poll.class);
//        System.out.println("Poll: " + createdPoll.getQuestion() + " created");
//
//        // List polls
//        Poll[] polls = restClient.get().uri(baseUrl + "/polls").retrieve().body(new ParameterizedTypeReference<Poll[]>() {});
//        assert polls != null;
//        System.out.println("Polls:");
//        for (Poll p : polls) {
//            System.out.println(" - " + p.getQuestion() + " (id=" + p.getId() + ")");
//        }
//        assertEquals(1, polls.length);
//
//        // Vote on Poll
//        Vote vote = new Vote();
//        vote.setPoll(createdPoll);
//        vote.setVoter(createdUser2);
//        vote.setVotesOn(yesOption);
//
//        Vote createdVote = restClient.post().uri(baseUrl + "/votes").body(vote).retrieve().body(Vote.class);
//
//        assert createdVote != null;
//        assertEquals("Yes", createdVote.getVotesOn().getCaption());
//        System.out.println("User " + vote.getVoter().getUsername() + " voted " + createdVote.getVotesOn().getCaption() + " on poll " + createdPoll.getQuestion());
//
//        // Change vote
//        Vote updateVote = new Vote();
//        updateVote.setVotesOn(noOption); // e.g. option "No"
//
//        Vote updatedVote = restClient.put().uri(baseUrl + "/votes/" + createdVote.getId()).body(updateVote).retrieve().body(Vote.class);
//
//        assert updatedVote != null;
//        assertEquals("No", updatedVote.getVotesOn().getCaption());
//        System.out.println("User " + vote.getVoter().getUsername() + " changed their vote to " + updatedVote.getVotesOn().getCaption() + " on poll " + createdPoll.getQuestion());
//
//        // List votes
//        Vote[] votes = restClient.get().uri(baseUrl + "/votes").retrieve().body(Vote[].class);
//        assert votes != null;
//        assertEquals(1, votes.length);
//        assertEquals("No", votes[0].getVotesOn().getCaption());
//        System.out.println("Votes:");
//        for (Vote v : votes) {
//            System.out.println(" - " + v.getVoter().getUsername() + " voted " + v.getVotesOn().getCaption() + " on " + v.getPoll().getQuestion());
//        }
//
//        // Delete poll
////        System.out.println("Poll " + createdPoll.getQuestion() + " deleted");
////        restClient.delete().uri(baseUrl + "/polls/" + createdPoll.getId()).retrieve().toBodilessEntity();
////
////        // Verify poll deleted
////        Poll[] remainingPolls = restClient.get().uri(baseUrl + "/polls").retrieve().body(Poll[].class);
////        assert remainingPolls != null;
////        assertEquals(0, remainingPolls.length);
//
//        // List votes
////       votes = restClient.get().uri(baseUrl + "/votes").retrieve().body(Vote[].class);
////        assert votes != null;
////        assertEquals(1, votes.length);
////        System.out.println("Votes:");
////        for (Vote v : votes) {
////            System.out.println(" - " + v.getVoter().getUsername() + " voted " + v.getOption().getCaption() + " on " + v.getPoll().getQuestion());
////        }
//    }
//}
