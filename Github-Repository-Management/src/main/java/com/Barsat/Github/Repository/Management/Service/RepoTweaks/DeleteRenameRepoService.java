package com.Barsat.Github.Repository.Management.Service.RepoTweaks;

import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeleteRenameRepoService {

    private final GetAuthenticatedUserName getAuthenticatedUserName;
    private final OAuthService oAuthService;

    public DeleteRenameRepoService(GetAuthenticatedUserName getAuthenticatedUserName , OAuthService oAuthService){
        this.getAuthenticatedUserName = getAuthenticatedUserName;
        this.oAuthService = oAuthService;
    }

    public String deleteRepo(String repoName){

        String username = getAuthenticatedUserName.getUsername();
        String accessToken = oAuthService.getAccessToken();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/vnd.github+json");

        String url = "https://github.com/" + username + "/" + repoName + "/settings";
        return url;

    }

}
