package com.example.newsoutletapi.services;

import com.example.newsoutletapi.model.Article;
import com.example.newsoutletapi.repos.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    public Article postNewArticle(Article article){
        article.setCreationDate(LocalDate.now());
        articleRepository.save(article);
        articleRepository.flush();
        return article;
    }

    public String deleteArticleById(Integer id){
        Optional<Article> post = articleRepository.findById(id);
        if(articleRepository.findById(id).isPresent()){
            post.get().setArticleTags(null);
        }

        articleRepository.deleteById(id);

        return "Post successfully deleted";
    }

    public Article editArticle(Article article){
         Article editedArticle = articleRepository.getById(article.getArticleId());
         editedArticle.setTitle(article.getTitle());
         editedArticle.setContent(article.getContent());
         editedArticle.setUser(article.getUser());
         editedArticle.setArticleTags(article.getArticleTags());
         return articleRepository.save(editedArticle);
    }

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public Page<Article> getAllArticlesWithPagination(int offset, int pageSize){
        return articleRepository.findAll(PageRequest.of(offset,pageSize));
    }

    public Article getArticle(Article article){
        return articleRepository.getById(article.getArticleId());
    }

    public Article getArticleById(Integer id){
        return articleRepository.getById(id);
    }

    public Article getArticleByTitle(String title){
        for(Article article:articleRepository.findAll()){
            if(article.getTitle().equals(title)){
                return article;
            }

        }
        return null;
    }

    public List<Article> getArticlesByTag(List<Integer> articleIds){
        return articleRepository.findArticlesByArticleIdIn(articleIds);
    }





    public List<Article> getPostsFromMonth(String month, String year){
        return null; //todo
    }



}
