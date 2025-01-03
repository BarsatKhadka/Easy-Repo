package com.Barsat.Github.Repository.Management.Models;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Models.RepoModels.TagEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class TheUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer masterId;

    @Column(nullable = false , unique = true)  //username can't be null and should be unique
    private String username;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Column(length = 10000)
    private String avatarUrl;

    private String bio ;

    private boolean isEnabled;

    private boolean emailVerified;

    //providers including self or github.
    @Enumerated(EnumType.STRING)
    private Provider provider;
    
    private String providerUserId;

    public TheUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public TheUser() {

    }


    @OneToMany(mappedBy = "masterUser" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<GithubRepoEntity> githubRepoEntity = new ArrayList<>();

    @OneToMany(mappedBy = "masterUser" , cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RepoCollectionsEntity> repoCollectionsEntity = new ArrayList<>();

    @OneToMany(mappedBy = "masterUser" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<TagEntity> tagEntity = new ArrayList<>();



    //keep adding more fields if needed.
}
