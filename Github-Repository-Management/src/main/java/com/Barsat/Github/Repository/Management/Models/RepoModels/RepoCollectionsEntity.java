package com.Barsat.Github.Repository.Management.Models.RepoModels;

import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//Entity to store your Group of repositories into custom collections like “Personal Projects,” “Team Work,” “Archived Repos”.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepoCollectionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collectionId;

    private String collectionName;

    @Lob
    private String collectionDescription;


    private LocalDateTime createdAt;

    //keeps a count of how many repositories are inside the collection.
    private int repositoryCount;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "master_id", referencedColumnName = "masterId"),
            @JoinColumn(name = "masterUserName", referencedColumnName = "username")
    })
    private TheUser masterUser;

    //because RepoCollections will many github repo entities
    @ManyToMany(mappedBy = "collectionsEntity")
    private List<GithubRepoEntity> githubRepo = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }



}
