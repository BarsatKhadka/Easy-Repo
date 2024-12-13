package com.Barsat.Github.Repository.Management.Service.RepoCollectionsService;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Repository.RepoCollectionsRepository;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import jakarta.transaction.Transactional;
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
    @Transactional         //manage hibernate session all at once. session closed before accessing so use transactional which wont allow that until it acheives its goal
    public void allCollection(String name){
        //find the user from userRepo and set his/her name on column along with collection name. (name comes from OAuthSuccessionHandler , so that it is never wrong)
        TheUser masterUser = userRepo.findByUsername(name);


        RepoCollectionsEntity repoCollectionsEntity = new RepoCollectionsEntity();
        repoCollectionsEntity.setCollectionName("All Repositories");

        //repoCollectionsEntity.setGithubRepo(githubReposRepository.findAll()); setting like this is wrong because i set those githubRepoEntity that do not have collections set in them


        repoCollectionsEntity.setCollectionDescription("Access all your repositories from here. This is a default collection and cannot be deleted.");
        repoCollectionsEntity.setMasterUser(masterUser);
        repoCollectionsEntity.setRepositoryCount(githubReposRepository.findAll().size());

        List<GithubRepoEntity> allRepoEntities = new ArrayList<>();
        for(GithubRepoEntity githubRepoEntity : githubReposRepository.findAll()){
            //set the repoCollectionEntity object to githubRepoEntity so that mapping works well
            //also just doing this will cause lazy load error becuase when i do .getCollectionsEntity it lazy loads and gives me proxy dummy thing instead of real value
            // that's how many to many works (by lazy loading)
            githubRepoEntity.getCollectionsEntity().add(repoCollectionsEntity);
            allRepoEntities.add(githubRepoEntity);
        }

        repoCollectionsEntity.setGithubRepo(allRepoEntities);

        repoCollectionsRepository.save(repoCollectionsEntity);
    }






}

