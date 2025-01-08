package com.Barsat.Github.Repository.Management.Controller.RepoCollections;

import com.Barsat.Github.Repository.Management.DTO.RepoCollectionDTO;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionCreate;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionGet;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionsAddRepo;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionsRemoveRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/easyrepo/collections")
public class CollectionsController {

    private final RepoCollectionCreate repoCollectionsCreate;
    private final RepoCollectionGet repoCollectionGet;
    private final RepoCollectionsAddRepo repoCollectionsAddRepo;
    private final RepoCollectionsRemoveRepo repoCollectionsRemoveRepo;

    public CollectionsController(RepoCollectionCreate repoCollectionsCreate, RepoCollectionGet repoCollectionGet , RepoCollectionsAddRepo repoCollectionsAddRepo , RepoCollectionsRemoveRepo repoCollectionsRemoveRepo) {
        this.repoCollectionsCreate = repoCollectionsCreate;
        this.repoCollectionGet = repoCollectionGet;
        this.repoCollectionsAddRepo = repoCollectionsAddRepo;
        this.repoCollectionsRemoveRepo = repoCollectionsRemoveRepo;
    }

    @GetMapping("/all")
    public RepoCollectionsEntity allRepoCollections(){
        return repoCollectionGet.allRepoCollections();

    }

    @GetMapping("/{collectionName}")
    public RepoCollectionsEntity getCollectionRepos(@PathVariable String collectionName){
        return repoCollectionGet.getSpecificCollection(collectionName);
    }

    @GetMapping("/allExistingCollections")
    public List<RepoCollectionsEntity> allExistingCollections(){
        return repoCollectionGet.allExistingCollections();
    }

    @PostMapping("/createCollection")
    public void createCollections(@RequestBody RepoCollectionDTO repoCollectionsDTO) {
        repoCollectionsCreate.createCollection(repoCollectionsDTO);
    }

    @PostMapping("/addRepoToCollection/{collectionId}")
    public void addRepoToCollection(@PathVariable Integer collectionId, @RequestBody RepoCollectionDTO repoCollectionsDTO) {
        repoCollectionsAddRepo.addRepo(collectionId,repoCollectionsDTO);

    }

    @PostMapping("/removeRepoFromCollection/{collectionId}")
    public void removeRepoFromCollection(@PathVariable Integer collectionId, @RequestBody RepoCollectionDTO repoCollectionsDTO) {
        repoCollectionsRemoveRepo.removeRepoFromCollection(collectionId,repoCollectionsDTO);
    }
}
