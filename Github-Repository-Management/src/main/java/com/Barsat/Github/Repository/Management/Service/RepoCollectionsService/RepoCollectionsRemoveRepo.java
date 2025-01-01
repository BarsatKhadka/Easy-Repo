package com.Barsat.Github.Repository.Management.Service.RepoCollectionsService;

import com.Barsat.Github.Repository.Management.DTO.RepoCollectionDTO;
import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Repository.GithubReposRepository;
import com.Barsat.Github.Repository.Management.Repository.RepoCollectionsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepoCollectionsRemoveRepo {
    private final RepoCollectionsRepository repoCollectionsRepository;
    private final GithubReposRepository githubReposRepository;

    public RepoCollectionsRemoveRepo(RepoCollectionsRepository repoCollectionsRepository , GithubReposRepository githubReposRepository){
        this.repoCollectionsRepository = repoCollectionsRepository;
        this.githubReposRepository = githubReposRepository;
    }

    public void removeRepoFromCollection(Integer collectionId, RepoCollectionDTO repoCollectionsDTO){

        List<Integer> IdsToRemove= repoCollectionsDTO.getGithubRepoIds();
        List<Integer> IdsThatCanBeRemoved= new ArrayList<>();


        RepoCollectionsEntity repoCollectionsEntity = repoCollectionsRepository.findByCollectionId(collectionId);

        if(repoCollectionsEntity != null){

            for(GithubRepoEntity githubRepoEntity: repoCollectionsEntity.getGithubRepo()){
                System.out.println(githubRepoEntity.getRepoId());
                if(IdsToRemove.contains(githubRepoEntity.getRepoId())){
                    IdsThatCanBeRemoved.add(githubRepoEntity.getRepoId());

                }
            }
            for(Integer IdToRemove : IdsThatCanBeRemoved){
                GithubRepoEntity githubRepoEntity = githubReposRepository.findByRepoId(IdToRemove);

                //very graceful way of removing mapping from github repo entity. We cant use cascade.remove on many to many as it can lead to danger consequences.
                repoCollectionsEntity.getGithubRepo().remove(githubRepoEntity);
                githubRepoEntity.getCollectionsEntity().remove(repoCollectionsEntity);

                repoCollectionsEntity.setRepositoryCount(repoCollectionsEntity.getGithubRepo().size());
            }
            repoCollectionsRepository.save(repoCollectionsEntity);
        }
    }


}
