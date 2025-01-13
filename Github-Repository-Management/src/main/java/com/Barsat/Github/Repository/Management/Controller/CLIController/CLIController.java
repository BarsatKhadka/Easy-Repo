package com.Barsat.Github.Repository.Management.Controller.CLIController;

import com.Barsat.Github.Repository.Management.Service.CLIService.CLIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("easyrepo/cli")
public class CLIController {

    private final CLIService cliService;

    public CLIController(CLIService cliService) {
        this.cliService = cliService;
    }

    @PostMapping("/execute")
    public String executeCLICommand(@RequestBody String command) {
        return cliService.processCommand(command);

    }



}
