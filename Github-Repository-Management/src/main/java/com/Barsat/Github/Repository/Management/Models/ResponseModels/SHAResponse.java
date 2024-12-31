package com.Barsat.Github.Repository.Management.Models.ResponseModels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class SHAResponse {

    //traditional way of @JsonProperty did work but took too much time. So getting the string response and getting the value from node and again returning
    //from string
    public String sha(String SHAResponse) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(SHAResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return rootNode.get(0).get("sha").asText();
    }
}
