package com.Barsat.Github.Repository.Management.Controller;

import com.Barsat.Github.Repository.Management.DTO.RepoCollectionDTO;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionCreate;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grms/collections")
public class CollectionsController {

    @Autowired
    private RepoCollectionCreate repoCollectionsCreate;

    @PostMapping("/createCollection")
    public void createCollections(@RequestBody RepoCollectionDTO repoCollectionsDTO) {
        repoCollectionsCreate.createCollection(repoCollectionsDTO);
    }
}
