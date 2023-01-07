package com.example.newsoutletapi.controllers;

import com.example.newsoutletapi.model.Article;
import com.example.newsoutletapi.model.RequestArticle;
import com.example.newsoutletapi.model.Tag;
import com.example.newsoutletapi.model.User;
import com.example.newsoutletapi.services.ArticleService;
import com.example.newsoutletapi.services.TagService;
import com.example.newsoutletapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
public class ArticleController {

    ArticleService articleService;
    TagService tagService;
    UserService userService;

    public ArticleController(ArticleService articleService, TagService tagService, UserService userService) {
        this.articleService = articleService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @PostMapping("/add")
    Article createArticle(@RequestBody RequestArticle article){
        Article newArticle = new Article();
        newArticle.setContent(article.getContent());
        newArticle.setCreationDate(LocalDate.now());
        newArticle.setTitle(article.getTitle());
        newArticle.setArticleTags(new ArrayList<>());
        System.out.println(article.getUser().getNickname());
        System.out.println(article.getUser().toString());
        User author = userService.getUserByNickname(article.getUser().getNickname());

        newArticle.setUser(author);
        articleService.postNewArticle(newArticle);

        for(String tagName:article.getArticleTags()){
            Tag newTag = new Tag();
            newTag.setName(tagName);
            newTag.setArticle(newArticle);
            tagService.addTag(newTag);
            newArticle.getArticleTags().add(newTag);
        }
        articleService.editArticle(newArticle);
        return newArticle;
    }

    @DeleteMapping("/{id}/delete")
    String deleteArticle(@PathVariable("id") Integer id){
        articleService.deleteArticleById(id);
        return "Post deleted successfully";
    }

    @PutMapping("/{id}/edit")
    String editArticle(@RequestBody Article article){
        articleService.editArticle(article);
        return "Post edited successfully";
    }

    @PutMapping("/{id}/verify")
    Article verifyArticle(@PathVariable Integer id){
        return articleService.verifyArticle(id);
    }

    @GetMapping("/{id}/get")
    Article getArticleById(@PathVariable Integer id){
        return articleService.getArticleById(id);
    }

    @GetMapping("/getAll")
    List<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    @GetMapping("getPagination/{offset}/{pageSize}")
    Page<Article> getPaginatedArticles(@PathVariable Integer offset, @PathVariable Integer pageSize){
        return articleService.getAllArticlesWithPagination(offset,pageSize);
    }

    @GetMapping("/getByTag/{name}")
    List<Article> getArticlesByTag(@PathVariable String name){
        return articleService.findArticlesByTagName(name);
        //return articleService.getArticlesByTag(articlesIds);
    }

    @GetMapping("/getVerified")
    List<Article> getVerifiedArticles(){
        return articleService.getVerified();
    }

    @GetMapping("/getUnverified")
    List<Article> getUnverifiedArticles(){
        return articleService.getUnverified();
    }

    @GetMapping("/getByAuthor/{id}")
    List<Article> getArticlesByAuthor(@PathVariable Integer id){
        return articleService.findArticlesByAuthor(id);
    }








}
