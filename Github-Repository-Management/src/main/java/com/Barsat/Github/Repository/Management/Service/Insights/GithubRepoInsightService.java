package com.Barsat.Github.Repository.Management.Service.Insights;

import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubRepoInsightService {

    private final GetAuthenticatedUserName getAuthenticatedUserName;

    public GithubRepoInsightService(GetAuthenticatedUserName getAuthenticatedUserName) {
        this.getAuthenticatedUserName = getAuthenticatedUserName;
    }


    public String getTotalLinesOfCode(String repoName){

        String username = getAuthenticatedUserName.getUsername();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://api.codetabs.com/v1/loc?github=" + username + "/" + repoName;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
        System.out.println(response.getBody());
        return response.getBody();



    }
}
