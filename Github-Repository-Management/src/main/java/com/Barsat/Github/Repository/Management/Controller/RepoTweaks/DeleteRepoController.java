package com.Barsat.Github.Repository.Management.Controller.RepoTweaks;

import com.Barsat.Github.Repository.Management.Service.RepoTweaks.DeleteRepoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easyRepo/repoTweaks/delete")
public class DeleteRepoController {

    private final DeleteRepoService deleteRepoService;
    public DeleteRepoController(DeleteRepoService deleteRepoService) {
        this.deleteRepoService = deleteRepoService;
    }

    @GetMapping("/{repoName}")
    public String deleteRepo(@PathVariable String repoName) {

        //This redirects you to settings , not directly delete it because when i give delete scope then before authorization it warns user saying this app
        //can delete your repo and they may not use the app.

        return deleteRepoService.deleteRepo(repoName);

    }



}
