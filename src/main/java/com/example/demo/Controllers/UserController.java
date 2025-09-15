package com.example.demo.Controllers;

import com.example.demo.PollManager;
import com.example.demo.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    final PollManager manager;

    public UserController(PollManager manager) {this.manager = manager;}

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
