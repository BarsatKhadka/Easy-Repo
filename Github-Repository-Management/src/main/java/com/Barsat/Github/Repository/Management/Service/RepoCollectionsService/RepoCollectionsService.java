package com.Barsat.Github.Repository.Management.Service.RepoCollectionsService;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Repository.RepoCollectionsRepository;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@ToString
@Getter
@Setter
public class RepoCollectionsService {

    private final GithubReposRepository githubReposRepository;
    private final RepoCollectionsRepository repoCollectionsRepository;
    private final UserRepo userRepo;

    public RepoCollectionsService(GithubReposRepository githubReposRepository, RepoCollectionsRepository repoCollectionsRepository, UserRepo userRepo) {
        this.githubReposRepository = githubReposRepository;
        this.repoCollectionsRepository = repoCollectionsRepository;
        this.userRepo = userRepo;
    }

    private String username;


    //Default created collection called all collection which contains all collection.
    @Transactional         //manage hibernate session all at once. session closed before accessing so use transactional which wont allow that until it acheives its goal
    public void allCollection(){
        List <GithubRepoEntity> allRepoEntities = new ArrayList<>();

        /* this logic prevents from creating allCollection every login as it checks if a entity already exists. If an entity already exists then it has repoCollectionsEntity ,
         we will load that EXISTING entity else make a new Entity.  This method 'all collection' is called every login and updates the list of existing
         repositories if anything is updated
         */
        RepoCollectionsEntity repoCollectionsEntity;

        boolean repoCollectionAlreadyExists = repoCollectionsRepository.existsByMasterUserUsername(username);

        if(repoCollectionAlreadyExists){
            //needed double check because just checking by masterUserUsername was returning many repoCollectionsEntity and i wanted to only check All repositories
            repoCollectionsEntity = repoCollectionsRepository.findByMasterUserUsernameAndCollectionName(username, "All Repositories");
            for(GithubRepoEntity githubRepoEntity : repoCollectionsEntity.getGithubRepo()){
                allRepoEntities.add(githubRepoEntity);
            }
        }
        else{
            repoCollectionsEntity = new RepoCollectionsEntity();
        }

        //find the user from userRepo and set his/her name on column along with collection name. (name comes from OAuthSuccessionHandler , so that it is never wrong)
        TheUser masterUser = userRepo.findByUsername(username);

        repoCollectionsEntity.setCollectionName("All Repositories");

        repoCollectionsEntity.setCollectionDescription("Access all your repositories from here. This is a default collection and cannot be deleted.");
        repoCollectionsEntity.setMasterUser(masterUser);
        repoCollectionsEntity.setRepositoryCount(githubReposRepository.findAll().size());

        //repoCollectionsEntity.setGithubRepo(githubReposRepository.findAll()); setting like this is wrong because i set those githubRepoEntity that do not have collections set in them


        for(GithubRepoEntity githubRepoEntity : githubReposRepository.findAll()){
            // check if allRepoEntities contain githubRepoEntity , if not set it , otherwise don't. Prevents duplicates.
            if(!allRepoEntities.contains(githubRepoEntity)){
                /*set the repoCollectionEntity object to githubRepoEntity so that mapping works well
            also just doing this will cause lazy load error becuase when i do .getCollectionsEntity it lazy loads and gives me proxy dummy thing instead of real value
            that's how many to many works (by lazy loading) */
                githubRepoEntity.getCollectionsEntity().add(repoCollectionsEntity);
                allRepoEntities.add(githubRepoEntity);
            }

        }

        repoCollectionsEntity.setGithubRepo(allRepoEntities);
        repoCollectionsRepository.save(repoCollectionsEntity);
    }


}

