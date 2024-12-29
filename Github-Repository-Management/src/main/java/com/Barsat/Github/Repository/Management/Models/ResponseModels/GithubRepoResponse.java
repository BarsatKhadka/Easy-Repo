package com.Barsat.Github.Repository.Management.Models.ResponseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@Data
public class GithubRepoResponse {

    @JsonProperty("id")
    private int RepositoryId;

    @JsonProperty("name")
    private String RepositoryName;

    @JsonProperty("full_name")
    private String RepositoryFullName;

    @JsonProperty("private")
    private boolean isPrivate;

    @JsonProperty("html_url")
    private String HtmlUrl;

    @JsonProperty("fork")
    private boolean isFork;

    @JsonProperty("description")
    private String Description;

    @JsonProperty("created_at")
    private String CreatedAt;

    @JsonProperty("updated_at")
    private String UpdatedAt;

    @JsonProperty("stargazers_count")
    private int StargazersCount;

    @JsonProperty("watchers_count")
    private int WatchersCount;

    @JsonProperty("language")
    private String Language;

    @JsonProperty("default_branch")
    private String DefaultBranch;



}
