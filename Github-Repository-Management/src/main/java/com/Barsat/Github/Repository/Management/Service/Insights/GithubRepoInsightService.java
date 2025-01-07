package com.Barsat.Github.Repository.Management.Service.Insights;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GithubRepoInsightService {

    private final GetAuthenticatedUserName getAuthenticatedUserName;
    private final OAuthService oAuthService;
    private final GithubReposRepository githubReposRepository;

    public GithubRepoInsightService(GetAuthenticatedUserName getAuthenticatedUserName , OAuthService oAuthService, GithubReposRepository githubReposRepository) {
        this.getAuthenticatedUserName = getAuthenticatedUserName;
        this.oAuthService = oAuthService;
        this.githubReposRepository = githubReposRepository;
    }


    public Map<String,List> getTotalLinesOfCode(String repoName){

        String username = getAuthenticatedUserName.getUsername();
        List<String> keys = new ArrayList<>();
        List<Integer> languages = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization" , "Bearer "+ oAuthService.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://api.github.com/repos/" + username + "/" + repoName+ "/" + "languages";


        ResponseEntity<Map<String,Integer>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });

        Map<String,Integer> responseMap = response.getBody();
        if(response.getBody() !=null){
            keys.addAll(responseMap.keySet());
            languages.addAll(responseMap.values());

        }

        return Map.of("keys",keys,"languages",languages);



    }

    public String getReadMe(String repoName) {
        String username = getAuthenticatedUserName.getUsername();

        GithubRepoEntity githubRepoEntity = githubReposRepository.findByName(repoName);

        String defaultBranch = "master";
        if(githubRepoEntity != null){
            defaultBranch = githubRepoEntity.getDefault_branch();
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization" , "Bearer "+ oAuthService.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://raw.githubusercontent.com/" + username + "/" + repoName + "/"+  defaultBranch + "/README.md";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();

    }
}
