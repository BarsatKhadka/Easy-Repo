package com.Barsat.Github.Repository.Management.Service.OAuthService;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Data
@Service
public class OAuthService {


    public String accessToken;

    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.github.client-secret}")
    private String clientSecret;

    RestTemplate restTemplate = new RestTemplate();

    public String generateAccessToken(String code) {


        //when i return back after final exam , the solution of this problem is that this client id and secret must be passed on the body
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        //i think the issue is i am passing these parameters not in the right way
        Map<String , String> request = new HashMap<>();
        request.put("client_id", clientId);
        request.put("client_secret", clientSecret);
        request.put("code", code);

        HttpEntity<Map<String,String>> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://github.com/login/oauth/access_token", requestEntity, String.class);
        return response.getBody();
    }

    public OAuthService() {

    }

}
