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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final TagService tags;
    private final TagRepository tagRepository;

    public ArticleService(ArticleRepository articleRepository, TagRepository tagRepository, TagService tags) {
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
        this.tags = tags;
    }
    public Article postNewArticle(Article article){
        article.setCreationDate(LocalDate.now());
        article.setVerifyInProgress(true);
        articleRepository.save(article);
        articleRepository.flush();
        return article;
    }

    public String deleteArticleById(Integer id){
        Optional<Article> post = articleRepository.findById(id);
        /*if(articleRepository.findById(id).isPresent()){
            post.get().setArticleTags(null);
        }
        for (Tag tag:tags.findTagsOfArticleById(id)){
            tagRepository.delete(tag);
            tagRepository.flush();
        }*/
        //System.out.println(articleRepository.getById(id).getArticleTags().get(0));
        //articleRepository.getById(id).getArticleTags().clear();
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
        editedArticle.setVerifyInProgress(false);
        editedArticle.setIsVerified(true);
        return articleRepository.save(editedArticle);
    }

    public Article declineArticle(Integer id){
        Article editedArticle = articleRepository.getById(id);
        editedArticle.setVerifyInProgress(false);
        editedArticle.setIsVerified(false);
        return articleRepository.save(editedArticle);
    }

    public Article markAsVerifyInProgress(Integer id){
        Article editedArticle = articleRepository.getById(id);
        editedArticle.setVerifyInProgress(true);
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

    public Optional<Article> getVerifiedArticleById(Integer id){
        Optional<Article> result = articleRepository.findById(id);
        try{
            if(result.isPresent() && result.get().getIsVerified()){
                return articleRepository.findById(id);
            }
            return Optional.empty();
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
            if(article.getVerifyInProgress()){
                result.add(article);
            }
        }
        return result;
    }

    public Optional<List<Article>> getVerified(){
        List<Article> result = new ArrayList<>();
        for(Article article:articleRepository.findAll()){
            if(article.getIsVerified()){
                result.add(article);
            }
        }
        return Optional.of(result);
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
        Collections.reverse(result);
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
