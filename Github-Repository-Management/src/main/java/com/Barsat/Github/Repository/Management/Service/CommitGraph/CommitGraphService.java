package com.Barsat.Github.Repository.Management.Service.CommitGraph;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Repository.RepoCollectionsRepository;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CommitGraphService {


    private final GetAuthenticatedUserName getAuthenticatedUserName;
    private final RepoCollectionsRepository repoCollectionsRepository;
    private final GithubReposRepository githubReposRepository;

    public CommitGraphService(GetAuthenticatedUserName getAuthenticatedUserName, RepoCollectionsRepository repoCollectionsRepository, GithubReposRepository githubReposRepository) {
        this.getAuthenticatedUserName = getAuthenticatedUserName;
        this.repoCollectionsRepository = repoCollectionsRepository;
        this.githubReposRepository = githubReposRepository;
    }

    public String getCommitForAllRepo(){

        List<GithubRepoEntity> githubRepoEntity = githubReposRepository.findAll();
        for(GithubRepoEntity githubRepoEntity1 : githubRepoEntity){
            System.out.println(githubRepoEntity1.getName());
        }

        RestTemplate restTemplate = new RestTemplate();

//        for(GithubRepoEntity githubRepoEntity: )
//        ResponseEntity<String> response = restTemplate.exchange()



        return "hello";
    }


}
