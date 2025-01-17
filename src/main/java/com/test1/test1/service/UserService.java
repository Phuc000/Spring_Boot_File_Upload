package com.test1.test1.service;


import com.test1.test1.model.User;
import com.test1.test1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        userRepository.deleteById(id);
    }

    public int getUserSize() {
        return (int) userRepository.count();
    }

    public String getMd5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    public User put(String userName, String password, String firstName, String lastName, String email, String role) throws NoSuchAlgorithmException {
        User user = new User();
        user.setUserName(userName);
        // hash the password in md5
        user.setPassword(getMd5(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(role);
        user.setCreatedAt(java.time.LocalDateTime.now());
        userRepository.save(user);
        return user;
    }

    public User login(String userName, String password) throws NoSuchAlgorithmException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(getMd5(password))) {
            return user;
        }
        return null;
    }

    public User signup(String userName, String password, String firstName, String lastName, String email) throws NoSuchAlgorithmException {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            return null;
        }
        System.out.println("signup");
        return put(userName, password, firstName, lastName, email, "user");
    }

    public User updateProfile(String username, String password, String firstName, String lastName) {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            return null;
        }
        if (!password.isEmpty()) {
            try {
                user.setPassword(getMd5(password));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        if (!firstName.isEmpty()) {
            user.setFirstName(firstName);
        }
        if (!lastName.isEmpty()) {
            user.setLastName(lastName);
        }
        userRepository.save(user);
        return user;
    }
}
