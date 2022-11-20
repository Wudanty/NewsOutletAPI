package com.example.newsoutletapi.services;

import com.example.newsoutletapi.model.Article;
import com.example.newsoutletapi.model.User;
import com.example.newsoutletapi.repos.UserRepository;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Integer id){
        return userRepository.findById(id).get();
    }

    public User getUserByNickname(String nickname){
        return userRepository.findAll().stream().filter(user -> user.getNickname().equals(nickname)).findFirst().orElseThrow();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public User updateUser(User user){
        User updatedUser = userRepository.getById(user.getUserId());
        updatedUser.setNickname(user.getNickname());
        updatedUser.setPassword(user.getPassword());
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
