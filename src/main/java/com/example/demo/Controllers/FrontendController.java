package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    // Forward /createpoll to index.html
    @RequestMapping("/createpoll")
    public String forwardCreatePoll() {
        return "forward:/index.html";
    }

    // Forward /vote/{id} to index.html
    @RequestMapping("/vote/{id}")
    public String forwardVote() {
        return "forward:/index.html";
    }
}
