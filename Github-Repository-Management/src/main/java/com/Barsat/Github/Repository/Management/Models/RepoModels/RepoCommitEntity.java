package com.Barsat.Github.Repository.Management.Models.RepoModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RepoCommitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;

    private String sha;

    private String date;

    private String url;

    @ManyToOne
    @JoinColumn(name="parent_repo_id", referencedColumnName = "repoId")
    private GithubRepoEntity githubRepoEntity;




}
