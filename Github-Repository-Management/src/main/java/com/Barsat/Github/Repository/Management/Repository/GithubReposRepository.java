package com.Barsat.Github.Repository.Management.Repository;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubReposRepository extends JpaRepository<GithubRepoEntity, Integer> {
    GithubRepoEntity findByGithubId(Integer id);
}
