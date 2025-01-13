package com.Barsat.Github.Repository.Management.Service.CLIService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CLIService {

    //first line of command should have these values or don't process.
    List<String> commandFirstLine = Arrays.asList("repo", "collections");
    List<String> repoCommandSecondLine = Arrays.asList("tree", "calendar", "loc" , "readMe" , "ovs" , "delete", "put");
    List<String> collectionCommandSecondLine = Arrays.asList("create", "rename", "delete");

    public String processCommand(String command) {
        String[] trim = command.split("\\+");
        if(trim.length < 3){
            return "Invalid command";
        }
        for(String trim1 : trim) {
            System.out.println(trim1);
        }
        return trim[0];

    }


}
