package com.Barsat.Github.Repository.Management.Service.OpenVsCode;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import org.springframework.stereotype.Service;

@Service
public class OpenVsCodeService {

    private final GithubReposRepository githubReposRepository;
    public OpenVsCodeService(GithubReposRepository githubReposRepository) {
        this.githubReposRepository = githubReposRepository;

    }

    public String openVsCodeUrl(String repoName){
        GithubRepoEntity githubRepoEntity = githubReposRepository.findByName(repoName);
        String htmlUrl = "";
        if(githubRepoEntity !=null){
            htmlUrl = githubRepoEntity.getHtml_url();
        }

        String vsCodeUrl = htmlUrl.replace("https://github.com/","https://github.dev/");

        return vsCodeUrl;
    }
}
