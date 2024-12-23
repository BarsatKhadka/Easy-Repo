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
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tagId;

    private String tagName;

    @ManyToOne
    private TheUser masterUser;

    @ManyToMany
    @JoinTable(
            name = "Tag & Repo",
            joinColumns = @JoinColumn(name = "Tag id" , referencedColumnName = "tagId"),
            inverseJoinColumns = @JoinColumn(name = "Github Repo Id" , referencedColumnName = "repoId")
    )
    @JsonBackReference
    private List<GithubRepoEntity> githubRepoEntityList;

}
