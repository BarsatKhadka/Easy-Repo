package com.Barsat.Github.Repository.Management.Models.ResponseModels;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class SHAResponse {

    //traditional way of @JsonProperty did work but took too much time. So getting the string response and getting the value from node and again returning
    //from string
    public String sha(String SHAResponse) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(SHAResponse);
        return rootNode.get(0).get("sha").asText();
    }
}
