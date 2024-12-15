package com.Barsat.Github.Repository.Management.Service.RepoCollectionsService;

import com.Barsat.Github.Repository.Management.DTO.RepoCollectionDTO;
import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Models.RequestModels.LoginRequest;
import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Repository.RepoCollectionsRepository;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepoCollectionCreate {

    @Autowired
    private RepoCollectionsRepository repoCollectionsRepository;

    @Autowired
    private GithubReposRepository githubReposRepository;

    @Autowired
    private RepoCollectionsService repoCollectionsService;


    @Autowired
    private UserRepo userRepo;


    public void createCollection(RepoCollectionDTO repoCollectionDTO){

        //to set the list of repositories to repoCollections. User will pass id , i will find by id and add it here then set this to repoEntities.
        List<GithubRepoEntity> githubRepoEntities = new ArrayList<>();

        //get the username and find the user to set the user in collections record.
        String username = repoCollectionsService.getUsername();

        TheUser masterUser = userRepo.findByUsername(username);

        RepoCollectionsEntity repoCollectionsEntity = new RepoCollectionsEntity();

        repoCollectionsEntity.setMasterUser(masterUser);
        repoCollectionsEntity.setCollectionName(repoCollectionDTO.getCollectionName());
        repoCollectionsEntity.setCollectionDescription(repoCollectionDTO.getCollectionDescription());

        for(Integer repoId : repoCollectionDTO.getGithubRepoIds()){
            GithubRepoEntity githubRepoEntity = githubReposRepository.findById(repoId).orElse(null);
            if(githubRepoEntity == null){
                throw new RuntimeException("Repo Not Found");
            }
            else{
                githubRepoEntities.add(githubRepoEntity);
            }
        }

        repoCollectionsEntity.setGithubRepo(githubRepoEntities);
        repoCollectionsEntity.setRepositoryCount(githubRepoEntities.size());
        repoCollectionsRepository.save(repoCollectionsEntity);
    }
}
