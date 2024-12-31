package com.Barsat.Github.Repository.Management.Repository;

import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCommitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommitRepository extends JpaRepository<RepoCommitEntity, Integer> {
    boolean existsByGithubRepoEntityRepoIdAndSha(Integer githubRepoId, String sha);
    boolean existsByGithubRepoEntityRepoId(Integer githubRepoId);

}
