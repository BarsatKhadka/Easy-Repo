package com.Barsat.Github.Repository.Management.Service.CommitGraph;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.ResponseModels.RepoCommitResponseModel;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Service.TreeServices.GetRepoSHAKey;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommitGraphService {


    private final GetAuthenticatedUserName getAuthenticatedUserName;
    private final GithubReposRepository githubReposRepository;
    private final GetRepoSHAKey getRepoSHAKey;


    public CommitGraphService(GetAuthenticatedUserName getAuthenticatedUserName, GithubReposRepository githubReposRepository, GetRepoSHAKey getRepoSHAKey) {
        this.getAuthenticatedUserName = getAuthenticatedUserName;
        this.githubReposRepository = githubReposRepository;
        this.getRepoSHAKey = getRepoSHAKey;

    }

    public String getCommitForAllRepo(String accessToken){

        RestTemplate restTemplate = new RestTemplate();

        List<GithubRepoEntity> githubRepoEntity = githubReposRepository.findAll();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/vnd.github+json");
        headers.set("Authorization", "Bearer "+ accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        for(GithubRepoEntity githubRepoEntity1 : githubRepoEntity){
            if(githubRepoEntity1!= null && !githubRepoEntity1.getName().isEmpty()){
                String repoName = githubRepoEntity1.getName();
                String username = getAuthenticatedUserName.getUsername();
                String sha = getRepoSHAKey.getSHA(repoName , username);

                String url = "https://api.github.com/repos/"+ username + "/" + repoName + "/commits" ;
                ResponseEntity<RepoCommitResponseModel[]> response = restTemplate.exchange(url, HttpMethod.GET, entity , RepoCommitResponseModel[].class);
                System.out.println(Arrays.toString(response.getBody()));
            }

        }



//        ResponseEntity<String> response = restTemplate.exchange()



        return "hello";
    }


}
