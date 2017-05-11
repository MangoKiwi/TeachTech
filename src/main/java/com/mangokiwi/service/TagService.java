package com.mangokiwi.service;

import com.mangokiwi.core.annotation.HandleEntityNotFound;
import com.mangokiwi.model.Tag;
import com.mangokiwi.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhenfeng on 5/10/17.
 */

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @HandleEntityNotFound
    public Tag getTagById(Long id){
        return tagRepository.findById(id);
    }

    @HandleEntityNotFound
    public List<Tag> getTagByUserId(Long userId){
        return tagRepository.findByUserId(userId);
    }

    public Tag update(Tag tag){
        return tagRepository.save(tag);
    }

    public Tag add(Tag tag){
        return tagRepository.save(tag);
    }

    public void deleteById(Long id){
        tagRepository.deleteById(id);
    }
}
