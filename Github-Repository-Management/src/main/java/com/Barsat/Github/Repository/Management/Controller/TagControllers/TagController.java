package com.Barsat.Github.Repository.Management.Controller.TagControllers;

import com.Barsat.Github.Repository.Management.DTO.TagDTO;
import com.Barsat.Github.Repository.Management.Service.TagService.TagCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easyrepo/tags")
public class TagController {

    @Autowired
    private TagCreateService tagCreateService;

    @PostMapping("/create")
    public String createTag(@RequestBody TagDTO tag) {
        tagCreateService.createTag(tag);
        return "Tag Successfully Created";
    }






}
