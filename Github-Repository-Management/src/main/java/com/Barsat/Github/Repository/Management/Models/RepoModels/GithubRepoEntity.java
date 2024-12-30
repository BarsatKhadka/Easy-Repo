package com.Barsat.Github.Repository.Management.Models.RepoModels;

import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class GithubRepoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer repoId;

    private int githubId;

    private String name;

    //going to store a long object because description can go pretty long
    @Lob
    private String description;

    private String language;

    private String html_url;

    private String fullName;

    private String created_at;

    private String updated_at;

    private boolean isPrivate;

    private boolean isFork;

    private int stargazers_count;

    private int watchers_count;

    private String default_branch;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "master_id", referencedColumnName = "masterId"),
            @JoinColumn(name = "masterUserName", referencedColumnName = "username")
    })
    private TheUser masterUser;

    @ManyToMany
    @JoinTable(
            name = "github_repo_&_collection",
            joinColumns = @JoinColumn(name = "repo_id" , referencedColumnName = "repoId"),
            inverseJoinColumns = @JoinColumn(name = "collection_id" , referencedColumnName = "collectionId")
    )
    @JsonBackReference
    private List<RepoCollectionsEntity> collectionsEntity;

    @ManyToMany(mappedBy = "githubRepoEntityList" , cascade = CascadeType.ALL )
    private List<TagEntity> tagEntity;

    @OneToMany(mappedBy = "githubRepoEntity")
    private List<RepoCommitEntity> repoCommitEntity;


}
