package com.Barsat.Github.Repository.Management.Repository;

import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCommitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommitRepository extends JpaRepository<RepoCommitEntity, Integer> {
    boolean existsByGithubRepoEntityRepoIdAndSha(Integer githubRepoId, String sha);
    boolean existsByGithubRepoEntityRepoId(Integer githubRepoId);

    //whenever writing sql query remember three important things , SELECT -> FROM and WHERE
    @Query("SELECT MAX(r.date) FROM RepoCommitEntity r WHERE r.githubRepoEntity.repoId = :RepoId")
    String findMaxDateByGithubRepoEntityRepoId(Integer RepoId);

}
