package com.Barsat.Github.Repository.Management.Models.RepoModels;

import com.Barsat.Github.Repository.Management.Models.TheUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GithubRepoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

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

    @ManyToOne
    @JoinColumn(name = "masterId")
    private TheUser master_user;



}
