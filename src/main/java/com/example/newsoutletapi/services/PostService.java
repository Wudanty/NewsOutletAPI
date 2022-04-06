package com.example.newsoutletapi.services;

import com.example.newsoutletapi.model.Post;
import com.example.newsoutletapi.model.User;
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
    @Autowired
    UserRepository userRepository;

    public Post postNew(Post post){
        User postAuthor = userRepository.getById(post.getAuthor().getUserId());
        postAuthor.getUserPosts().add(post);
        return postRepository.save(post);
    }

    public String deletePostById(Integer id){
        postRepository.deleteById(id);
        return "Post successfully deleted";
    }

    public Post editPost(Post post){
         Post editedPost = postRepository.getById(post.getPostId());
         editedPost.setTitle(post.getTitle());
         editedPost.setContent(post.getContent());
         editedPost.setAuthor(post.getAuthor());
         editedPost.setTags(post.getTags());
         return postRepository.save(editedPost);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }



}
