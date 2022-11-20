package com.example.newsoutletapi.repos;

import com.example.newsoutletapi.model.Article;
import com.example.newsoutletapi.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findArticlesByArticleIdIn(List<Integer> articleIds);

}
