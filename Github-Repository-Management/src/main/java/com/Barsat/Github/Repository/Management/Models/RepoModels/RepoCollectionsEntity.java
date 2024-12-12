package com.Barsat.Github.Repository.Management.Models.RepoModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


//Entity to store your Group of repositories into custom collections like “Personal Projects,” “Team Work,” “Archived Repos”.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepoCollectionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String collectionName;

    @Lob
    private String collectionDescription;

    private LocalDateTime createdAt;

    //keeps a count of how many repositories are inside the collection.
    private int repositoryCount;

    private LocalDateTime updatedAt;



}
