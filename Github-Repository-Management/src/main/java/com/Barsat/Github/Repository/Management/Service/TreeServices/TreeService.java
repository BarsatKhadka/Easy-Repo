package com.Barsat.Github.Repository.Management.Service.TreeServices;

import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TreeService {

    private final GetRepoSHAKey getRepoSHAKey;
    private final OAuthService oAuthService;


    RestTemplate restTemplate = new RestTemplate();

    public TreeService(GetRepoSHAKey getRepoSHAKey , OAuthService oAuthService) {
        this.getRepoSHAKey = getRepoSHAKey;
        this.oAuthService = oAuthService;
    }

    public String getTree() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization" , "Bearer "+ "accessToken");
        headers.set("Authorization" , "Bearer "+ oAuthService.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange( "https://api.github.com/repos/barsatKhadka/Github-Repository-Management-System/git/trees/"+getRepoSHAKey.getSHA() , HttpMethod.GET, entity, String.class);
        return response.getBody();
    }




}