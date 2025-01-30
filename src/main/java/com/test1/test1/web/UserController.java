package com.test1.test1.web;

import com.test1.test1.model.User;
import com.test1.test1.service.UserService;
import com.test1.test1.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
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
    public Map<String, String> login(@RequestBody Map<String, String> payload) throws NoSuchAlgorithmException {
        String username = payload.get("username");
        String password = payload.get("password");
        User user = userService.login(username, password);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        String token = JwtUtil.generateToken(username);
        return Map.of("token", token, "firstName", user.getFirstName(), "lastName", user.getLastName());
    }

    @PostMapping("/update_profile")
    public User updateProfile(@RequestBody Map<String, String> payload) throws NoSuchAlgorithmException {
        String username = payload.get("username") == null ? "" : payload.get("username");
        String password = payload.get("password") == null ? "" : payload.get("password");
        String firstName = payload.get("firstName") == null ? "" : payload.get("firstName");
        String lastName = payload.get("lastName") == null ? "" : payload.get("lastName");
        User user = userService.updateProfile(username, password, firstName, lastName);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    @GetMapping("/profile")
    public User getProfile(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String username = claims.getSubject();
        return userService.getUserByUsername(username);
    }
}
