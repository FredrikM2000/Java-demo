package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    final PollManager manager = new PollManager();

    public UserController() {}

    @GetMapping
    public Collection<User> allUsers() {
        return manager.getUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        manager.addUser(user);
        return user;
    }
}
