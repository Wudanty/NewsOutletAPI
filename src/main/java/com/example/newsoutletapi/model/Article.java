package com.example.newsoutletapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer articleId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 3000)
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "article")
    @JsonManagedReference
    private List<Tag> articleTags;
    private LocalDate creationDate;
    private Boolean isVerified;
    private Boolean verifyInProgress;
    private Boolean isDeclined;
    @Column(length = 100000)
    private byte[] picture;

}
