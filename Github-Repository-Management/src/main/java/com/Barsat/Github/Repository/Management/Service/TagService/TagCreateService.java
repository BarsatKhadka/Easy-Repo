package com.Barsat.Github.Repository.Management.Service.TagService;

import com.Barsat.Github.Repository.Management.DTO.TagDTO;
import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.TagEntity;
import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Repository.TagRepository;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagCreateService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GithubReposRepository githubReposRepository;

    @Autowired
    private GetAuthenticatedUserName getAuthenticatedUserName;

    public void createTag(TagDTO tag){

        //list to set in githubRepoEntity List
        List<GithubRepoEntity> githubRepoEntities = new ArrayList<>();


        String username = getAuthenticatedUserName.getUsername();

        TheUser masterUser = userRepo.findByUsername(username);

        TagEntity newTag = new TagEntity();
        newTag.setTagName(tag.getTagName());
        newTag.setMasterUser(masterUser);


        for (Integer id : tag.getGithubRepoEntityListIds()){
            GithubRepoEntity githubRepoEntity = githubReposRepository.findById(id).orElse(null);
            if(githubRepoEntity == null){
                throw new RuntimeException("Repo Not Found");
            }
            else{
                githubRepoEntities.add(githubRepoEntity);
            }

        }
        newTag.setGithubRepoEntityList(githubRepoEntities);
        tagRepository.save(newTag);

    }

}
