package com.Barsat.Github.Repository.Management.Models.ResponseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SHA {

    @JsonProperty("sha")
    private String sha;
}
