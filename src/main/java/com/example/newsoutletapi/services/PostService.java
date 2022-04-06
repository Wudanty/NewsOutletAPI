package com.example.newsoutletapi.services;

import com.example.newsoutletapi.model.Post;
import com.example.newsoutletapi.repos.PostRepository;
import com.example.newsoutletapi.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Post postNew(Post post){
        return postRepository.save(post);
    }

    public String deletePostById(Integer id){
        postRepository.deleteById(id);
        return "Post successfully deleted";
    }

    public List<Post> getUserPostsByUserId(Integer id){
        List<Post> allPosts = postRepository.findAll();
        List<Post> userPosts = new ArrayList<>();
        for(Post item:allPosts){
            if(item.getPostId().equals(id)){
                userPosts.add(item);
            }
        }
        return userPosts;
    }



}
