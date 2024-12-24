package com.Barsat.Github.Repository.Management.Service.OAuthService;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Data
@Service
public class OAuthService {

    private String code;

    public String accessToken;

    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.github.client-secret}")
    private String clientSecret;



    private final OAuth2AuthorizedClientService authorizedClientService;


    public OAuthService(OAuth2AuthorizedClientService authorizedClientService) {

        this.authorizedClientService = authorizedClientService;
    }

    public void generateAccessToken(OAuth2AuthenticationToken oAuth2AuthenticationToken , String clientRegistrationId ) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(clientRegistrationId, oAuth2AuthenticationToken.getName());

        if(client !=null){
            accessToken = client.getAccessToken().getTokenValue();
        }
        else{
            System.out.println("Client is null");
        }

    }


}
