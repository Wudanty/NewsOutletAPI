package com.example.newsoutletapi.controllers;

import com.example.newsoutletapi.model.Post;
import com.example.newsoutletapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/add")
    String postNew(@RequestBody Post post){
        postService.postNew(post);
        return "Post submitted successfully";
    }

    @DeleteMapping("/{id}/delete")
    String deletePost(@PathVariable("id") Integer id){
        postService.deletePostById(id);
        return "Post deleted successfully";
    }

    @PutMapping("/{id}/edit")
    String editPost(@RequestBody Post post){
        postService.editPost(post);
        return "Post edited successfully";
    }

    @GetMapping("/getAll")
    List<Post> getAllPosts(){
        return postService.getAllPosts();
    }



}
