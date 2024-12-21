package com.Barsat.Github.Repository.Management.Service.TagService;

import com.Barsat.Github.Repository.Management.DTO.TagDTO;
import com.Barsat.Github.Repository.Management.Models.RepoModels.TagEntity;
import com.Barsat.Github.Repository.Management.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagCreateService {

    @Autowired
    private TagRepository tagRepository;

    public void createTag(TagDTO tag){
        TagEntity newTag = new TagEntity();
        newTag.setTagName(tag.getTagName());
        tagRepository.save(newTag);

    }

}
