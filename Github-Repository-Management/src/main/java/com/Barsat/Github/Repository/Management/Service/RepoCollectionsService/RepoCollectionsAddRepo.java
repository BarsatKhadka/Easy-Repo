package com.Barsat.Github.Repository.Management.Service.RepoCollectionsService;

import com.Barsat.Github.Repository.Management.DTO.RepoCollectionDTO;
import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Repository.RepoCollectionsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RepoCollectionsAddRepo {

    private final GithubReposRepository githubReposRepository;
    private final RepoCollectionsRepository repoCollectionsRepository;

    public RepoCollectionsAddRepo(GithubReposRepository githubReposRepository, RepoCollectionsRepository repoCollectionsRepository) {
        this.githubReposRepository = githubReposRepository;
        this.repoCollectionsRepository = repoCollectionsRepository;
    }

    public void addRepo(Integer collectionId , RepoCollectionDTO repoCollectionDTO) {
        List<String> githubRepoNames = repoCollectionDTO.getGithubRepoNames();

        Set<GithubRepoEntity> addGithubRepoEntities = new HashSet<>();

        RepoCollectionsEntity repoCollectionsEntity = repoCollectionsRepository.findByCollectionId(collectionId);
        if(repoCollectionsEntity != null) {
            repoCollectionsEntity.getGithubRepo().forEach(githubRepo -> {
                addGithubRepoEntities.add(githubRepo);
            });
            for(String githubRepoName : githubRepoNames) {
                GithubRepoEntity githubRepoEntity = githubReposRepository.findByName(githubRepoName);
                if(!addGithubRepoEntities.contains(githubRepoEntity)) {

                    addGithubRepoEntities.add(githubRepoEntity);
                    repoCollectionsEntity.setGithubRepo(addGithubRepoEntities);

                    //i was stuck here and i read my code for allCollections and figured it out.
                    githubRepoEntity.getCollectionsEntity().add(repoCollectionsEntity);

                    repoCollectionsEntity.setRepositoryCount(addGithubRepoEntities.size());

                }



            }


            repoCollectionsRepository.save(repoCollectionsEntity);



        }

    }

}
