package com.Barsat.Github.Repository.Management.Models.ResponseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LOCResponseModel {

    @JsonProperty("language")
    private String languages;

    @JsonProperty("files")
    private String files;

    @JsonProperty("linesOfCode")
    private String linesOfCode;

    @JsonProperty("blanks")
    private String blankLines;

    @JsonProperty("comments")
    private String comments;

    @JsonProperty("lines")
    private String totalLines;
}
