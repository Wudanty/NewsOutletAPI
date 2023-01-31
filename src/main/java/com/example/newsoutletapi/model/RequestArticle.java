package com.example.newsoutletapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Data
public class RequestArticle {

    private String title;
    private String content;
    private User user;
    private List<String> articleTags;
    private LocalDate creationDate;
    private byte[] picture;

}
