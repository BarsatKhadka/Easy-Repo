package com.Barsat.Github.Repository.Management.Service.CLIService;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CLIService {

    private final GetAuthenticatedUserName getAuthenticatedUserName;
    private final GithubReposRepository githubReposRepository;
    public CLIService(GetAuthenticatedUserName getAuthenticatedUserName, GithubReposRepository githubReposRepository) {
        this.getAuthenticatedUserName = getAuthenticatedUserName;
        this.githubReposRepository = githubReposRepository;
    }

    //first line of command should have these values or don't process.
    List<String> commandFirstLine = Arrays.asList("repo", "collections");
    List<String> repoCommandSecondLine = Arrays.asList("tree", "calendar", "loc" , "readMe" , "ovs" , "delete", "put");
    List<String> collectionCommandSecondLine = Arrays.asList("create", "rename", "delete");

    public String processCommand(String command) {
        String[] trim = command.split("\\+");
        if(trim.length < 3){
            return "Invalid command";
        }
        if(!commandFirstLine.contains(trim[0])){
            return "commands start with 'repo' or 'collections'";
        }
        if(trim[0].equals("repo") && trim.length != 3 || trim[0].equals("repo") && !repoCommandSecondLine.contains(trim[1])){
            return "Repo command syntax error";
        }

        String repoName = trim[2].substring(0, trim[2].length()-1);
        boolean repoExist = githubReposRepository.existsByMasterUserUsernameAndName(getAuthenticatedUserName.getUsername() , repoName);

        if(!repoExist){
            return "Repo does not exist";
        }
        if(trim[1].equals("tree")){
            GithubRepoEntity githubRepoEntity = githubReposRepository.findByName(repoName);
            return command + String.valueOf(githubRepoEntity.getRepoId());
        }
        if(trim[1].equals("calendar")){
            return command;
        }
        if(trim[1].equals("loc")){
            GithubRepoEntity githubRepoEntity = githubReposRepository.findByName(repoName);
            if(githubRepoEntity != null){
                return command + repoName;
            }
            else {
                return "Repo does not exist";
            }
        }

        if(trim[1].equals("readMe")){
            GithubRepoEntity githubRepoEntity = githubReposRepository.findByName(repoName);
            if(githubRepoEntity != null){
                return command + repoName;
            }
            else {
                return "Repo does not exist";
            }

        }
        if(trim[1].equals("ovs")){
            GithubRepoEntity githubRepoEntity = githubReposRepository.findByName(repoName);
            if(githubRepoEntity != null){
                String htmlUrl = "";
                if(githubRepoEntity !=null){
                    htmlUrl = githubRepoEntity.getHtml_url();
                }

                String vsCodeUrl = htmlUrl.replace("https://github.com/","https://github.dev/");

                return "Open+with+Vs+Code+" + repoName + "=" +vsCodeUrl;
            }
            else{
                return "Repo does not exist";
            }
        }



        return command;

    }


}
