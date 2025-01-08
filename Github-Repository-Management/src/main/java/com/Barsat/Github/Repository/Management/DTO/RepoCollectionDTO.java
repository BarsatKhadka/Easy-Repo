package com.Barsat.Github.Repository.Management.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class RepoCollectionDTO {
    private String collectionName;
    private List<Integer> githubRepoIds = new ArrayList<>();
    private List<String> githubRepoNames = new ArrayList<>();

}
