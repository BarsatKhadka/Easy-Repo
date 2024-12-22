package com.Barsat.Github.Repository.Management.DTO;

import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TagDTO {
    private String tagName;

    private List<Integer> githubRepoEntityListIds;

}
