package com.Barsat.Github.Repository.Management.Service.CommitGraph;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCommitEntity;
import com.Barsat.Github.Repository.Management.Models.ResponseModels.RepoCommitResponseModel;
import com.Barsat.Github.Repository.Management.Models.ResponseModels.SHAResponse;
import com.Barsat.Github.Repository.Management.Repository.CommitRepository;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Service.TreeServices.GetRepoSHAKey;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CommitGraphService {


    private final GetAuthenticatedUserName getAuthenticatedUserName;
    private final GithubReposRepository githubReposRepository;
    private final GetRepoSHAKey getRepoSHAKey;
    private  RepoCommitEntity repoCommitEntity;
    private final CommitRepository commitRepository;
    private final SHAResponse shaResponse;


    public CommitGraphService(GetAuthenticatedUserName getAuthenticatedUserName, GithubReposRepository githubReposRepository, GetRepoSHAKey getRepoSHAKey , CommitRepository commitRepository,
                              SHAResponse shaResponse) {
        this.getAuthenticatedUserName = getAuthenticatedUserName;
        this.githubReposRepository = githubReposRepository;
        this.getRepoSHAKey = getRepoSHAKey;
        this.commitRepository = commitRepository;
        this.shaResponse = shaResponse;



    }

    RestTemplate restTemplate = new RestTemplate();


    public String getCommitForAllRepo(String accessToken){

        List<GithubRepoEntity> githubRepoEntity = githubReposRepository.findAll();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/vnd.github+json");
        headers.set("Authorization", "Bearer "+ accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String username = getAuthenticatedUserName.getUsername();

//        getting commit for all repo starts from getting all repositories (repoEntities)
        for(GithubRepoEntity githubRepoEntity1 : githubRepoEntity){

            //if they are not null , then hit the /commits end point , get all commits and parse the required response then set that to repoCommitEntity.
            if(githubRepoEntity1!= null && !githubRepoEntity1.getName().isEmpty()){

                String repoName = githubRepoEntity1.getName();

                //this gets the latest commit
                String sha = getRepoSHAKey.getSHA(repoName, username);

                boolean commitEntityExists = commitRepository.existsByGithubRepoEntityRepoIdAndSha(githubRepoEntity1.getRepoId(), sha);
                System.out.println(commitEntityExists + "first check" + "(this is upto date)" + "for" + githubRepoEntity1.getName());

                if(!commitEntityExists) {
                    boolean result = commitRepository.existsByGithubRepoEntityRepoId(githubRepoEntity1.getRepoId());
                    System.out.println(result + "second check" + "(this says that recent commit is not upto date)" + "for" + githubRepoEntity1.getName());

                    if(result){


                    }

                    else {

                        String url = "https://api.github.com/repos/" + username + "/" + repoName + "/commits";
                        ResponseEntity<RepoCommitResponseModel[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, RepoCommitResponseModel[].class);

                        for (RepoCommitResponseModel repoCommitResponseModel : response.getBody()) {
                            RepoCommitEntity repoCommitEntity = new RepoCommitEntity();
                            repoCommitEntity.setGithubRepoEntity(githubRepoEntity1);
                            repoCommitEntity.setDate(repoCommitResponseModel.getCommit().getAuthor().getDate());
                            repoCommitEntity.setMessage(repoCommitResponseModel.getCommit().getMessage());
                            repoCommitEntity.setSha(repoCommitResponseModel.getSha());
                            commitRepository.save(repoCommitEntity);
//                            System.out.println(repoCommitResponseModel.getCommit().getMessage());
//                    System.out.println(repoCommitResponseModel.toString());
                        }
                    }

                }


            }

        }



//        ResponseEntity<String> response = restTemplate.exchange()



        return "hello";
    }


}
