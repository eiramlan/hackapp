package com.example.hackapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to HackApp! Use the API for user registration and login.";
    }

    // Insecure user registration
    @PostMapping("/register")  // Corrected path
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Vulnerable login (for demonstrating SQL injection)
    @PostMapping("/login")  // Corrected path
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "Welcome, " + user.getUsername();
        }
        return "Invalid username or password";
    }
}
