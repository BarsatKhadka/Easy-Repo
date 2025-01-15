package com.Barsat.Github.Repository.Management.Service.CLIService;

import com.Barsat.Github.Repository.Management.DTO.RepoCollectionDTO;
import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Repository.RepoCollectionsRepository;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionCreate;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionRename;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class CLIService {

    private final GetAuthenticatedUserName getAuthenticatedUserName;
    private final GithubReposRepository githubReposRepository;
    private final RepoCollectionsRepository repoCollectionsRepository;
    private final RepoCollectionCreate repoCollectionCreate;
    private final RepoCollectionRename repoCollectionsRename;
    public CLIService(GetAuthenticatedUserName getAuthenticatedUserName, GithubReposRepository githubReposRepository
            , RepoCollectionsRepository repoCollectionsRepository , RepoCollectionCreate repoCollectionCreate , RepoCollectionRename repoCollectionRename) {
        this.getAuthenticatedUserName = getAuthenticatedUserName;
        this.githubReposRepository = githubReposRepository;
        this.repoCollectionsRepository = repoCollectionsRepository;
        this.repoCollectionCreate = repoCollectionCreate;
        this.repoCollectionsRename = repoCollectionRename;
    }

    //first line of command should have these values or don't process.
    List<String> commandFirstLine = Arrays.asList("repo", "collections");
    List<String> repoCommandSecondLine = Arrays.asList("tree", "calendar", "loc" , "readMe" , "ovs" , "delete", "rename");
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

        //i am doing repoName because repoName does not mean repo is coming but anything that is on index 2
        if(trim[0].equals("collections") && trim[1].equals("delete") && trim.length ==3 ){
            RepoCollectionsEntity repoCollectionsEntity = repoCollectionsRepository.findByCollectionName(repoName);

            if(repoCollectionsEntity != null){
                for(GithubRepoEntity githubRepoEntity: repoCollectionsEntity.getGithubRepo()){
                    githubRepoEntity.getCollectionsEntity().remove(repoCollectionsEntity);
                }
                repoCollectionsRepository.delete(repoCollectionsEntity);

                return command + repoName;
            }
            else{
                return "Repo does not exist";
            }
        }
        if(trim[0].equals("collections") && trim[1].equals("rename")){
            System.out.println("THis fucking compiled");
            if(trim.length != 4){
                return "Collection rename command syntax error";
            }
            RepoCollectionsEntity repoCollectionsEntity = repoCollectionsRepository.findByCollectionName(trim[2]);
            if(repoCollectionsEntity !=null){

                repoCollectionsRename.renameRepoCollection(trim[2], trim[3].substring(0,trim[3].length()-1));
                return command;

            }

        }

        if(trim[0].equals("collections") && trim[1].equals("create")){
            //this starts with is < and ends with is >, = because = gets into every code
            if(trim.length !=4 || (!trim[3].startsWith("%3C") || !trim[3].endsWith("%3E="))  ){
                return "Collection command syntax error";
            }

            String collectionName = trim[2];
            String repoNames = trim[3].substring(3, trim[3].length()-4);

            // %2C is comma
            if(repoNames.contains("%2C")){
                List<Integer> repoIds = new ArrayList<>();
                List<String> allRepos = Arrays.asList(repoNames.split("%2C"));
                for(String repo: allRepos){
                    System.out.println(repo);
                    GithubRepoEntity githubRepoEntity = githubReposRepository.findByName(repo);
                    if(githubRepoEntity != null){
                        repoIds.add(githubRepoEntity.getRepoId());
                    }
                }
                if(repoIds.isEmpty()){
                    return "Repos does not exist";
                }
                RepoCollectionDTO repoCollectionDTO = new RepoCollectionDTO();
                repoCollectionDTO.setCollectionName(collectionName);
                repoCollectionDTO.setGithubRepoIds(repoIds);
                repoCollectionCreate.createCollection(repoCollectionDTO);



            }
            else{
                List<String> names = new ArrayList<>();
                GithubRepoEntity githubRepoEntity = githubReposRepository.findByName(repoNames);
                if(githubRepoEntity != null){
                    System.out.println("this printed");
                    names.add(githubRepoEntity.getName());
                    RepoCollectionDTO repoCollectionDTO = new RepoCollectionDTO();
                    repoCollectionDTO.setCollectionName(collectionName);
                    repoCollectionDTO.setGithubRepoNames(names);
                    repoCollectionCreate.createCollection(repoCollectionDTO);
                }
                else{
                    return "Repo does not exist";
                }


            }

            return "Collection created";

        }

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

        if(trim[1].equals("delete")){
            GithubRepoEntity githubRepoEntity = githubReposRepository.findByName(repoName);
            if(githubRepoEntity != null){
                return command + repoName;
            }
            else{
                return "Repo does not exist";
            }
        }

        if(trim[1].equals("rename")){
            GithubRepoEntity githubRepoEntity = githubReposRepository.findByName(repoName);
            if(githubRepoEntity != null){
                return command + repoName;
            }
        }

        if(trim[1].equals("collections") && trim[2].equals("delete") && trim.length !=3){
            return "Delete only one collection at a time.";
        }



        return command;

    }


}
