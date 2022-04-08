package com.example.newsoutletapi.services;

import com.example.newsoutletapi.model.Post;
import com.example.newsoutletapi.repos.PostRepository;
import com.example.newsoutletapi.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public void postNew(Post post){
        postRepository.save(post);
    }

    public String deletePostById(Integer id){
        postRepository.deleteById(id);
        return "Post successfully deleted";
    }

    public Post editPost(Post post){
         Post editedPost = postRepository.getById(post.getPostId());
         editedPost.setTitle(post.getTitle());
         editedPost.setContent(post.getContent());
         editedPost.setUser(post.getUser());
         editedPost.setTags(post.getTags());
         return postRepository.save(editedPost);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }



}
