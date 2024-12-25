package com.Barsat.Github.Repository.Management.Controller.TreeController;

import com.Barsat.Github.Repository.Management.Service.TreeServices.TreeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }


    @GetMapping("/getTree/{repoId}")
    public String getTree(@PathVariable Integer repoId) {
        return treeService.getTree(repoId);

    }
}
