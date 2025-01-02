package com.Barsat.Github.Repository.Management.Service.CLIService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CLIService {

    //first line of command should have these values or don't process.
    List<String> commandFirstLine = Arrays.asList("repo", "collections");
    List<String> commandSecondLine = Arrays.asList("view", "delete");

    public String processCommand(String command) {
        String trim = command.trim();
        return trim;

    }


}
