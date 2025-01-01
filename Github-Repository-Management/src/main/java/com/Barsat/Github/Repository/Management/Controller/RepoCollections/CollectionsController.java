package com.Barsat.Github.Repository.Management.Controller.RepoCollections;

import com.Barsat.Github.Repository.Management.DTO.RepoCollectionDTO;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionCreate;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionGet;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionsAddRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easyrepo/collections")
public class CollectionsController {

    private final RepoCollectionCreate repoCollectionsCreate;
    private final RepoCollectionGet repoCollectionGet;
    private final RepoCollectionsAddRepo repoCollectionsAddRepo;

    public CollectionsController(RepoCollectionCreate repoCollectionsCreate, RepoCollectionGet repoCollectionGet , RepoCollectionsAddRepo repoCollectionsAddRepo) {
        this.repoCollectionsCreate = repoCollectionsCreate;
        this.repoCollectionGet = repoCollectionGet;
        this.repoCollectionsAddRepo = repoCollectionsAddRepo;
    }

    @GetMapping("/all")
    public RepoCollectionsEntity allRepoCollections(){
        return repoCollectionGet.allRepoCollections();

    }


    @PostMapping("/createCollection")
    public void createCollections(@RequestBody RepoCollectionDTO repoCollectionsDTO) {
        repoCollectionsCreate.createCollection(repoCollectionsDTO);
    }

    @PostMapping("/addRepoToCollection/{collectionId}")
    public void addRepoToCollection(@PathVariable Integer collectionId, @RequestBody RepoCollectionDTO repoCollectionsDTO) {
        repoCollectionsAddRepo.addRepo(collectionId,repoCollectionsDTO);


    }
}
