package com.Barsat.Github.Repository.Management.Controller.RepoTweaks;

import com.Barsat.Github.Repository.Management.Service.RepoTweaks.DeleteRepoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/easyRepo/repoTweaks/delete")
public class DeleteRepoController {

    private final DeleteRepoService deleteRepoService;
    public DeleteRepoController(DeleteRepoService deleteRepoService) {
        this.deleteRepoService = deleteRepoService;
    }

    @DeleteMapping("/{repoName}")
    public String deleteRepo(@PathVariable String repoName) {

        //This redirects you to settings , not directly delete it because when i give delete scope then before authorization it warns user saying this app
        //can delete your repo and they may not use the app.

        return deleteRepoService.deleteRepo(repoName);

    }



}
