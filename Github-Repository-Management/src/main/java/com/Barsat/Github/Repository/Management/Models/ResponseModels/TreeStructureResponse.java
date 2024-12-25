package com.Barsat.Github.Repository.Management.Models.ResponseModels;


import java.util.List;

public class TreeStructureResponse {

    private String sha;

    private String url;

    private List<tree> tree;

    private boolean truncated;

    //this is static for a reason. This is independent of outer class causing memory optimization. And the grouping is also logical because it is the structure of above field.
    public static class tree {
        private String path;
        private String type;
        private String sha;
        private String url;
        private List<tree> children;

    }


}
