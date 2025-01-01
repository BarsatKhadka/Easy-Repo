package com.Barsat.Github.Repository.Management.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class CommitGraphDTO {

    private String sha;
    private String message;
    private String date;

    public CommitGraphDTO(String sha, String message, String date) {
        this.sha = sha;
        this.message = message;
        this.date = date;
    }

}
