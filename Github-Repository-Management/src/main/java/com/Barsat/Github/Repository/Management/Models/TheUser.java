package com.Barsat.Github.Repository.Management.Models;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
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

    @Column(nullable = false)  //username can't be null and should be unique
    private String username;

    @Column(unique = true)
    private String email;

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


    @OneToMany(mappedBy = "master_user" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GithubRepoEntity> githubRepoEntity = new ArrayList<>();

    //add more fields if needed.
}
