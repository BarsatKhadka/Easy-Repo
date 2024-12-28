package com.Barsat.Github.Repository.Management.Models.ResponseModels;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

//To string to visualize output in console.
@ToString
@Getter
@Setter
public class TreeStructureResponse {

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("url")
    private String url;

    @JsonProperty("tree")
    private List<tree> tree;

    @JsonProperty("truncated")
    private boolean truncated;

    //this is static for a reason. This is independent of outer class causing memory optimization. And the grouping is also logical because it is the structure of above field.
    @ToString
    @Getter
    @Setter
    public static class tree {
        @JsonProperty("path")
        private String path;

        @JsonProperty("mode")
        private String mode;

        @JsonProperty("type")
        private String type;

        @JsonProperty("size")
        private String size;

        @JsonProperty("sha")
        private String sha;

        @JsonProperty("url")
        private String url;

        @JsonProperty("tree")
        private List<tree> children;

    }


}
