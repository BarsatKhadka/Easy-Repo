package com.Barsat.Github.Repository.Management.Models.TreeModels;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Nodes.Node;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class TreeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer treeId;


    @OneToOne(mappedBy = "treeEntity")
    @JoinColumn(name = "repoId" , referencedColumnName = "repoId")
    private GithubRepoEntity repoEntity;



}
