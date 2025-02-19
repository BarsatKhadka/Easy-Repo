package com.Barsat.Github.Repository.Management.Repository;

import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCollectionsRepository extends JpaRepository<RepoCollectionsEntity, Integer> {
    /*to reference parent id column , first reference the field name of the parent in the entity. For here it's theUser 'masterUser' , then the field inside theUser you want to
     find by */
    boolean existsByMasterUserUsername(String masterUserName);
    RepoCollectionsEntity findByCollectionId(Integer collectionId);

    RepoCollectionsEntity findByMasterUserUsernameAndCollectionName(String masterUserUsername, String collectionName);

    List<RepoCollectionsEntity> findAllByMasterUserUsername(String masterUserUsername);


    RepoCollectionsEntity findByCollectionName(String repoName);
}

