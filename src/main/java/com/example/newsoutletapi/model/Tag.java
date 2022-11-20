package com.example.newsoutletapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer tagId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "article_id")
    @JsonBackReference
    private Article article;
}
