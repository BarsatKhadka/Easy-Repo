package com.Barsat.Github.Repository.Management.Service.GithubFetchService;

import com.Barsat.Github.Repository.Management.Models.ResponseModels.GithubRepoResponse;
import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
@Setter
public class GithubFetchService {

    private OAuthService oAuthService;


    public GithubFetchService(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    private final String baseUrl = "https://api.github.com";

    RestTemplate restTemplate = new RestTemplate();

    public List<GithubRepoResponse> fetchRepositories(String userName , String accessToken) {
        String finalUrl =  baseUrl + "/users/" + userName + "/repos";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept","application/vnd.github+json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<GithubRepoResponse[]> response = restTemplate.exchange(finalUrl, HttpMethod.GET, entity, GithubRepoResponse[].class);
        return Arrays.asList(response.getBody());
    }







//    String GetReposfinalUrl = baseUrl + ;
//    ResponseEntity<String> githubRepoResponse = restTemplate.exchange(baseUrl)




}
