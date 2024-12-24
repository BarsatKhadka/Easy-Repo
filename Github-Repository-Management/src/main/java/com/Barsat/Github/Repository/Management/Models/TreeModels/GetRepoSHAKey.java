package com.Barsat.Github.Repository.Management.Models.TreeModels;

import com.Barsat.Github.Repository.Management.Models.ResponseModels.SHA;
import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetRepoSHAKey {

    @Autowired
    private OAuthService oAuthService;

    RestTemplate restTemplate = new RestTemplate();

    public String getTrees() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization" , "Bearer "+ oAuthService.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("https://api.github.com/repos/barsatKhadka/Github-Repository-Management-System/commits?per_page=1", HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());
        return response.getBody();

    }

}
