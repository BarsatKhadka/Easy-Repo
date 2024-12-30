package com.Barsat.Github.Repository.Management.Service.CommitGraph;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCommitEntity;
import com.Barsat.Github.Repository.Management.Models.ResponseModels.RepoCommitResponseModel;
import com.Barsat.Github.Repository.Management.Repository.CommitRepository;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Service.TreeServices.GetRepoSHAKey;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommitGraphService {


    private final GetAuthenticatedUserName getAuthenticatedUserName;
    private final GithubReposRepository githubReposRepository;
    private final GetRepoSHAKey getRepoSHAKey;
    private  RepoCommitEntity repoCommitEntity;
    private final CommitRepository commitRepository;


    public CommitGraphService(GetAuthenticatedUserName getAuthenticatedUserName, GithubReposRepository githubReposRepository, GetRepoSHAKey getRepoSHAKey , CommitRepository commitRepository) {
        this.getAuthenticatedUserName = getAuthenticatedUserName;
        this.githubReposRepository = githubReposRepository;
        this.getRepoSHAKey = getRepoSHAKey;
        this.commitRepository = commitRepository;



    }

    public String getCommitForAllRepo(String accessToken){

        RestTemplate restTemplate = new RestTemplate();

        List<GithubRepoEntity> githubRepoEntity = githubReposRepository.findAll();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/vnd.github+json");
        headers.set("Authorization", "Bearer "+ accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String username = getAuthenticatedUserName.getUsername();

        for(GithubRepoEntity githubRepoEntity1 : githubRepoEntity){
            if(githubRepoEntity1!= null && !githubRepoEntity1.getName().isEmpty()){
                String repoName = githubRepoEntity1.getName();
                String sha = getRepoSHAKey.getSHA(repoName , username);

                String url = "https://api.github.com/repos/"+ username + "/" + repoName + "/commits" ;
                ResponseEntity<RepoCommitResponseModel[]> response = restTemplate.exchange(url, HttpMethod.GET, entity , RepoCommitResponseModel[].class);

                for(RepoCommitResponseModel repoCommitResponseModel : response.getBody()){
                    RepoCommitEntity repoCommitEntity = new RepoCommitEntity();
                    repoCommitEntity.setGithubRepoEntity(githubRepoEntity1);
                    repoCommitEntity.setDate(repoCommitResponseModel.getCommit().getAuthor().getDate());
                    repoCommitEntity.setMessage(repoCommitResponseModel.getCommit().getMessage());
                    repoCommitEntity.setSha(repoCommitResponseModel.getSha());
                    commitRepository.save(repoCommitEntity);
                    System.out.println(repoCommitResponseModel.getCommit().getMessage());
//                    System.out.println(repoCommitResponseModel.toString());
                }

            }

        }



//        ResponseEntity<String> response = restTemplate.exchange()



        return "hello";
    }


}
