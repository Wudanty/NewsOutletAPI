package com.example.newsoutletapi.services;

import com.example.newsoutletapi.model.Article;
import com.example.newsoutletapi.model.User;
import com.example.newsoutletapi.repos.UserRepository;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User getUser(Integer id){
        return userRepository.findById(id).get();
    }

    public User getUserByNickname(String nickname){
        return userRepository.findAll().stream().filter(user -> user.getNickname().equals(nickname)).findFirst().orElseThrow();
    }

    public void addUser(User user){
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public User updateUser(User user){
        User updatedUser = userRepository.findById(user.getUserId()).get();
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        updatedUser.setNickname(user.getNickname());
        updatedUser.setPassword(encodedPassword);
        userRepository.save(updatedUser);
        return updatedUser;
    }

    public List<Article> getUserPosts(Integer id){
        User user = userRepository.findById(id).get();
        return user.getUserArticles();

    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
