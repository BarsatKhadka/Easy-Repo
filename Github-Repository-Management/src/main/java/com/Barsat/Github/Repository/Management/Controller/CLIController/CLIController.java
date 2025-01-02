package com.Barsat.Github.Repository.Management.Controller.CLIController;

import com.Barsat.Github.Repository.Management.Service.CLIService.CLIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("easyrepo/cli")
public class CLIController {

    private final CLIService cliService;

    public CLIController(CLIService cliService) {
        this.cliService = cliService;
    }

    @GetMapping("/execute")
    public String executeCLICommand(@RequestBody String command) {
        return cliService.processCommand(command);

    }



}
