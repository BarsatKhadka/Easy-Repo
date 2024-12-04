package com.Barsat.Github.Repository.Management.Models.RepoModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RepositoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String githubRepoId;

    private String username;

    private String repoDescription;

    private String language;

    private boolean repoVisibilityPrivate;

    private String createdAt;

    private String updatedAt;



}
