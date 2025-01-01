package com.Barsat.Github.Repository.Management.Controller.RepoTweaks;

import com.Barsat.Github.Repository.Management.Service.RepoTweaks.DeleteRenameRepoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easyRepo/repoTweaks/deleteRename")
public class DeleteRenameRepoController {

    private final DeleteRenameRepoService deleteRenameRepoService;
    public DeleteRenameRepoController(DeleteRenameRepoService deleteRenameRepoService) {
        this.deleteRenameRepoService = deleteRenameRepoService;
    }

    @GetMapping("/{repoName}")
    public String deleteRenameRepo(@PathVariable String repoName) {

        //This redirects you to settings , not directly delete it because when i give delete scope then before authorization it warns user saying this app
        //can delete your repo and they may not use the app.

        return deleteRenameRepoService.deleteRepo(repoName);

    }



}
