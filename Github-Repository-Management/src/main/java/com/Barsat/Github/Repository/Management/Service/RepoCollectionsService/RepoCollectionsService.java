package com.Barsat.Github.Repository.Management.Service.RepoCollectionsService;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Repository.RepoCollectionsRepository;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ToString
public class RepoCollectionsService {

    @Autowired
    private GithubReposRepository githubReposRepository;

    @Autowired
    private RepoCollectionsRepository repoCollectionsRepository;

    @Autowired
    private UserRepo userRepo;

    
    //Default created collection called all collection which contains all collection.
    public void allCollection(String name){
        //find the user from userRepo and set his/her name on column along with collection name. (name comes from OAuthSuccessionHandler , so that it is never wrong)
        TheUser masterUser = userRepo.findByUsername(name);

        RepoCollectionsEntity repoCollectionsEntity = new RepoCollectionsEntity();
        repoCollectionsEntity.setCollectionName("All Repositories");

        repoCollectionsEntity.setCollectionDescription("Access all your repositories from here. This is a default collection and cannot be deleted.");
        repoCollectionsEntity.setMasterUser(masterUser);
        repoCollectionsEntity.setRepositoryCount(githubReposRepository.findAll().size());

        repoCollectionsRepository.save(repoCollectionsEntity);
    }






}

