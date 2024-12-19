package com.Barsat.Github.Repository.Management.Controller.RepoCollections;

import com.Barsat.Github.Repository.Management.DTO.RepoCollectionDTO;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionCreate;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easyrepo/collections")
public class CollectionsController {

    @Autowired
    private RepoCollectionCreate repoCollectionsCreate;

    @Autowired
    private RepoCollectionGet repoCollectionGet;

    @GetMapping("/all")
    public RepoCollectionsEntity allRepoCollections(){
        return repoCollectionGet.allRepoCollections();

    }


    @PostMapping("/createCollection")
    public void createCollections(@RequestBody RepoCollectionDTO repoCollectionsDTO) {
        repoCollectionsCreate.createCollection(repoCollectionsDTO);
    }
}
