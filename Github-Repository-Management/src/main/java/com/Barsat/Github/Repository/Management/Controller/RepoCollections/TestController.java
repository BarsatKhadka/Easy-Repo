package com.Barsat.Github.Repository.Management.Controller.RepoCollections;


import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
     private OAuthService oAuthService;

    RestTemplate restTemplate = new RestTemplate();


    @GetMapping("/repoContents")
    public String getRepoContents() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization", "Bearer "+ "accessToken");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("https://api.github.com/repos/barsatKhadka/Portfoilo/contents/", HttpMethod.GET, entity, String.class);
        return response.getBody();

    }

    @GetMapping("/getCommit")
    public String getTrees() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization" , "Bearer "+ "accessToken");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("https://api.github.com/repos/barsatKhadka/Github-Repository-Management-System/commits?per_page=1", HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());
        return response.getBody();

    }

    @GetMapping("/getTree")
    public String getTree() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization" , "Bearer "+ "accessToken");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange( "https://api.github.com/repos/barsatKhadka/Github-Repository-Management-System/git/trees/5f5b1aa8dc34536fac013fbb1015e2de3f972d70" , HttpMethod.GET, entity, String.class);
        return response.getBody();

    }

}
