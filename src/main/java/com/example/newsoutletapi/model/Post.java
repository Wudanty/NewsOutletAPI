package com.example.newsoutletapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer postId;
    private String title;
    private String content;
    @ManyToOne
    private User author;

}
