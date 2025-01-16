package com.test1.test1.web;

import com.test1.test1.model.User;
import com.test1.test1.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody Map<String, String> payload) throws NoSuchAlgorithmException {
        String username = payload.get("username");
        String password = payload.get("password");
        String email = payload.get("email");
        String firstName = "";
        String lastName = "";
        System.out.println("signup: " + username + " " + password + " " + email);
        User user = userService.signup(username, password, firstName, lastName, email);
        if (user == null) {
            throw new RuntimeException("User already exists");
        }
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> payload) throws NoSuchAlgorithmException {
        String username = payload.get("username");
        String password = payload.get("password");
        User user = userService.login(username, password);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }
}
