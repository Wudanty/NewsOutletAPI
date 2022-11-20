package com.example.newsoutletapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;
import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Article> userArticles;

}
