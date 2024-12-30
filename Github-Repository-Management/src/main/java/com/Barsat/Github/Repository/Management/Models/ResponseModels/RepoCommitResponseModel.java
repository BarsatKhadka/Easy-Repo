package com.Barsat.Github.Repository.Management.Models.ResponseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class RepoCommitResponseModel {

    @JsonProperty("sha")
    public String sha;

    @JsonProperty("commit")
    public Commit commit;


    @Getter
    @Setter
    @ToString
    public static class Commit {

        @JsonProperty("author")
        private Author author;

        @Getter
        @Setter
        @ToString
        public static class Author{
            @JsonProperty("date")
            private String date;
        }

        @JsonProperty("message")
        private String message;

    }






}
