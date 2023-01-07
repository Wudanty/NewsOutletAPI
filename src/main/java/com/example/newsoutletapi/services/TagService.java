package com.example.newsoutletapi.services;

import com.example.newsoutletapi.model.Article;
import com.example.newsoutletapi.model.Tag;
import com.example.newsoutletapi.repos.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag addTag(Tag tag){
        tagRepository.save(tag);
        tagRepository.flush();
        return tag;
    }

    public boolean tagExists(String tagName){
        List<Tag> tags = tagRepository.findAll();
        for(Tag tag:tags){
            if(tag.getName().equals(tagName)){
                return true;
            }
        }
        return false;
    }

    public Tag removeTag(Tag tag){
        tagRepository.delete(tag);
        return tag;
    }

    public String removeTagById(Integer id){
        tagRepository.deleteById(id);
        return "removed tag with id = " + id;
    }


    public List<Article> findArticlesByTagName(String name) {
        return tagRepository.findAllByName(name).stream().map(Tag::getArticle).collect(Collectors.toList());
    }
}
