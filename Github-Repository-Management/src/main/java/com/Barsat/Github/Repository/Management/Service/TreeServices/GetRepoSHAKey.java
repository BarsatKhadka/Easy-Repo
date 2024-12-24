package com.Barsat.Github.Repository.Management.Service.TreeServices;

import com.Barsat.Github.Repository.Management.Models.ResponseModels.SHAResponse;
import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Getter
@Setter
public class GetRepoSHAKey {

    private String accessToken;

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private SHAResponse shaResponse;

    RestTemplate restTemplate = new RestTemplate();

    public String getSHA()  {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization" , "Bearer "+ oAuthService.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //all response is fetched.
        ResponseEntity<String> response = restTemplate.exchange("https://api.github.com/repos/barsatKhadka/Github-Repository-Management-System/commits?per_page=1", HttpMethod.GET, entity, String.class);

        //pass all response and get only sha (in string).
        return shaResponse.sha(response.getBody());

    }



}
