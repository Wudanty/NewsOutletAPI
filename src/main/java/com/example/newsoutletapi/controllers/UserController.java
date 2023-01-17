package com.example.newsoutletapi.controllers;
import com.example.newsoutletapi.model.Article;
import com.example.newsoutletapi.model.User;
import com.example.newsoutletapi.services.UserService;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){this.userService = userService;}

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return userService.getUser(id);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public ResponseEntity<String> addUser(@RequestBody User user){
        String emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        if(user.getEmail().matches(emailPattern)) {
            userService.addUser(user);
            return ResponseEntity.ok().body("User created successfully");
        }else{
            return ResponseEntity.badRequest().body("Please write a correct email");
        }

    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @GetMapping("/hi")
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping("/getByNickname/{nickname}")
    public User getByNickname(@PathVariable String nickname){
        return userService.getUserByNickname(nickname);
    }

    @GetMapping("/get/{id}/getPosts")
    public List<Article> getUserPosts(@PathVariable("id") Integer id){
        return userService.getUserPosts(id);
    }
}
