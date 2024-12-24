package com.Barsat.Github.Repository.Management.Service.GithubFetchService;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.ResponseModels.GithubRepoResponse;
import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Setter
public class GithubFetchSaveService {

    private final OAuthService oAuthService;
    private final GithubReposRepository githubReposRepository;
    private final UserRepo userRepo;



    public GithubFetchSaveService(OAuthService oAuthService, GithubReposRepository githubReposRepository, UserRepo userRepo) {
        this.oAuthService = oAuthService;
        this.githubReposRepository = githubReposRepository;
        this.userRepo = userRepo;
    }

    private final String baseUrl = "https://api.github.com";

    RestTemplate restTemplate = new RestTemplate();

    //this is running from OAuthSuccessionHandler , meaning it does not fetch on every refresh but every login new data is fetched. This is to save calls.
    public void fetchSaveRepositories(String userName , String accessToken) {


        String finalUrl =  baseUrl + "/users/" + userName + "/repos";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept","application/vnd.github+json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<GithubRepoResponse[]> response = restTemplate.exchange(finalUrl, HttpMethod.GET, entity, GithubRepoResponse[].class);

        //find the current user in Userrepo
        TheUser theUser = userRepo.findByUsername(userName);
        System.out.println(userName);


        for(GithubRepoResponse githubRepoResponse : response.getBody()){

            //avoid duplicate calls.
            GithubRepoEntity repositoryEntity = githubReposRepository.findByGithubId(githubRepoResponse.getRepositoryId());

//            if repositoryEntity is not found by githubId meaning the githubId doesnot exist yet then make a new entity or else update on same entity
            //doing it on same entity will update the data if same githubId is found
            if(repositoryEntity == null){
                repositoryEntity = new GithubRepoEntity();
            }

            //set everything from the api response to repository entity
            repositoryEntity.setGithubId(githubRepoResponse.getRepositoryId());
            repositoryEntity.setDescription(githubRepoResponse.getDescription());
            repositoryEntity.setFork(githubRepoResponse.isFork());
            repositoryEntity.setLanguage(githubRepoResponse.getLanguage());
            repositoryEntity.setCreated_at(githubRepoResponse.getCreatedAt());
            repositoryEntity.setUpdated_at(githubRepoResponse.getUpdatedAt());
            repositoryEntity.setName(githubRepoResponse.getRepositoryName());
            repositoryEntity.setHtml_url(githubRepoResponse.getHtmlUrl());
            repositoryEntity.setFullName(githubRepoResponse.getRepositoryFullName());
            repositoryEntity.setPrivate(githubRepoResponse.isPrivate());
            repositoryEntity.setWatchers_count(githubRepoResponse.getWatchersCount());
            repositoryEntity.setStargazers_count(githubRepoResponse.getStargazersCount());

            //set the user of the repository so that foreign key can be mapped
            repositoryEntity.setMasterUser(theUser);

            githubReposRepository.save(repositoryEntity);

        }

    }


}
