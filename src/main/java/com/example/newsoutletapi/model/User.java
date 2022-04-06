package com.example.newsoutletapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.persistence.*;

@Entity(name="users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    private String nickname;
    private String password;
    @OneToMany
    private List<Post> userPosts;

}
