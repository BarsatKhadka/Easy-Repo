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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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


    //i will modify this to be called individually for each repo rather than generating all before hand on another commit.
    public void createUpdateCommitsForRepo(String accessToken , int repoId){

        GithubRepoEntity githubRepoEntity = githubReposRepository.findByRepoId(repoId);
        System.out.println(githubRepoEntity.getName());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/vnd.github+json");
        headers.set("Authorization", "Bearer "+ accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String username = getAuthenticatedUserName.getUsername();

            //if they are not null , then hit the /commits end point , get all commits and parse the required response then set that to repoCommitEntity.
            if(githubRepoEntity!= null && !githubRepoEntity.getName().isEmpty()) {

                String repoName = githubRepoEntity.getName();

                //this gets the latest commit
                String sha = getRepoSHAKey.getSHA(repoName, username);

                boolean commitEntityExists = commitRepository.existsByGithubRepoEntityRepoIdAndSha(githubRepoEntity.getRepoId(), sha);
                System.out.println(commitEntityExists + "first check" + "(this is upto date)" + "for" + githubRepoEntity.getName());

                if (!commitEntityExists) {
                    boolean result = commitRepository.existsByGithubRepoEntityRepoId(githubRepoEntity.getRepoId());
                    System.out.println(result + "second check" + "(this says that recent commit is not upto date)" + "for" + githubRepoEntity.getName());

                    if (result) {
                        //getting the current max Date in database
                        String maxDate = commitRepository.findMaxDateByGithubRepoEntityRepoId(githubRepoEntity.getRepoId());

                        //look at commits since that max Date to fetch any new commits after that date
                        String url = "https://api.github.com/repos/" + username + "/" + repoName + "/commits" + "?since=" + maxDate;

                        //fetch after that Date
                        ResponseEntity<RepoCommitResponseModel[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, RepoCommitResponseModel[].class);

                        //set them as a repo entity.
                        for (RepoCommitResponseModel repoCommitResponseModel : response.getBody()) {
                            RepoCommitEntity repoCommitEntity = new RepoCommitEntity();
                            repoCommitEntity.setGithubRepoEntity(githubRepoEntity);
                            repoCommitEntity.setMessage(repoCommitResponseModel.getCommit().getMessage());
                            repoCommitEntity.setDate(repoCommitResponseModel.getCommit().getAuthor().getDate());
                            repoCommitEntity.setMessage(repoCommitResponseModel.getCommit().getMessage());
                            repoCommitEntity.setSha(repoCommitResponseModel.getSha());
                            commitRepository.save(repoCommitEntity);
                        }

                    } else {

                        String url = "https://api.github.com/repos/" + username + "/" + repoName + "/commits";
                        ResponseEntity<RepoCommitResponseModel[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, RepoCommitResponseModel[].class);

                        for (RepoCommitResponseModel repoCommitResponseModel : response.getBody()) {
                            RepoCommitEntity repoCommitEntity = new RepoCommitEntity();
                            repoCommitEntity.setGithubRepoEntity(githubRepoEntity);
                            repoCommitEntity.setDate(repoCommitResponseModel.getCommit().getAuthor().getDate());
                            repoCommitEntity.setMessage(repoCommitResponseModel.getCommit().getMessage());
                            repoCommitEntity.setSha(repoCommitResponseModel.getSha());
                            commitRepository.save(repoCommitEntity);
                        }
                    }

                }


            }


    }

    public String getCommitsOfRepo(String accessToken, int repoId){
        createUpdateCommitsForRepo(accessToken, repoId);
        return "hello";

    }





}
