package com.Barsat.Github.Repository.Management.Service.RepoCollectionsService;

import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Repository.RepoCollectionsRepository;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.stereotype.Service;

@Service
public class RepoCollectionRename {
    private final RepoCollectionsRepository repoCollectionsRepository;
    private final GetAuthenticatedUserName getAuthenticatedUserName;

    public RepoCollectionRename(RepoCollectionsRepository repoCollectionsRepository, GetAuthenticatedUserName getAuthenticatedUserName) {
        this.repoCollectionsRepository = repoCollectionsRepository;
        this.getAuthenticatedUserName = getAuthenticatedUserName;
    }

    public void renameRepoCollection(String oldName, String newName) {
        System.out.println(oldName + " -> " + newName);
        String username = getAuthenticatedUserName.getUsername();
        RepoCollectionsEntity repoCollectionsEntity = repoCollectionsRepository.findByMasterUserUsernameAndCollectionName(username , oldName);
        if(repoCollectionsEntity != null) {
            repoCollectionsEntity.setCollectionName(newName);
            repoCollectionsRepository.save(repoCollectionsEntity);
        }

    }
}
