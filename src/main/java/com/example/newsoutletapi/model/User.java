package com.example.newsoutletapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Post> userPosts;

}
