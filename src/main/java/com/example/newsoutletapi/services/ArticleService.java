package com.example.newsoutletapi.services;

import com.example.newsoutletapi.model.Article;
import com.example.newsoutletapi.model.Tag;
import com.example.newsoutletapi.repos.ArticleRepository;
import com.example.newsoutletapi.repos.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    public ArticleService(ArticleRepository articleRepository, TagRepository tagRepository) {
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
    }
    public Article postNewArticle(Article article){
        article.setCreationDate(LocalDate.now());
        article.setIsVerified(false);
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
         editedArticle.setIsVerified(article.getIsVerified());
         return articleRepository.save(editedArticle);
    }

    public Article verifyArticle(Integer id){
        Article editedArticle = articleRepository.getById(id);
        editedArticle.setIsVerified(true);
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

    public Optional<Article> getArticleById(Integer id){
        try{
           return articleRepository.findById(id);
        }catch(Exception e){
            return Optional.empty();
        }
    }

    public Article getArticleByTitle(String title){
        for(Article article:articleRepository.findAll()){
            if(article.getTitle().equals(title)){
                return article;
            }

        }
        return null;
    }

    public List<Article> getUnverified(){
        List<Article> result = new ArrayList<>();
        for(Article article:articleRepository.findAll()){
            if(!article.getIsVerified()){
                result.add(article);
            }
        }
        return result;
    }

    public List<Article> getVerified(){
        List<Article> result = new ArrayList<>();
        for(Article article:articleRepository.findAll()){
            if(article.getIsVerified()){
                result.add(article);
            }
        }
        return result;
    }

    public List<Article> findArticlesByTagName(String name) {
        List<Article> articlesWithTag = tagRepository.findAllByName(name).stream().map(Tag::getArticle).collect(Collectors.toList());
        List<Article> result = new ArrayList<>();
        for(Article article:articlesWithTag){
            if(article.getIsVerified()){
                result.add(article);
            }
        }
        return result;
    }

    public List<Article> findArticlesByAuthor(Integer id){
        List<Article> result = new ArrayList<>();
        for(Article article:articleRepository.findAll()){
            if(article.getUser().getUserId() == id){
                result.add(article);
            }
        }
        return result;
    }

    public List<Article> findArticlesByKeyWord(String keyWord){
        List<Article> searchResult = new ArrayList<>();
        for(Article article:articleRepository.findAll()){
            if(article.getIsVerified() && article.getTitle().contains(keyWord)){
                searchResult.add(article);
            }
        }
        return searchResult;
    }



    public List<Article> getPostsFromMonth(String month, String year){
        return null; //todo
    }



}
