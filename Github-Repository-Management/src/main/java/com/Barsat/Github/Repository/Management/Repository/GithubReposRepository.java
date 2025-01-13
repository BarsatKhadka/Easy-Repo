package com.Barsat.Github.Repository.Management.Repository;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GithubReposRepository extends JpaRepository<GithubRepoEntity, Integer> {
    GithubRepoEntity findByGithubId(Integer id);
    boolean existsByMasterUserUsernameAndName(String masterUsername , String name);

    GithubRepoEntity findByRepoId(int repoId);
    GithubRepoEntity findByName(String repoName);
}
