package com.Barsat.Github.Repository.Management.Controller.RepoCollections;

import com.Barsat.Github.Repository.Management.DTO.RepoCollectionDTO;
import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/easyrepo/collections")
public class CollectionsController {

    private final RepoCollectionCreate repoCollectionsCreate;
    private final RepoCollectionGet repoCollectionGet;
    private final RepoCollectionsAddRepo repoCollectionsAddRepo;
    private final RepoCollectionsRemoveRepo repoCollectionsRemoveRepo;
    private final RepoCollectionRename repoCollectionRename;

    public CollectionsController(RepoCollectionCreate repoCollectionsCreate, RepoCollectionGet repoCollectionGet , RepoCollectionsAddRepo repoCollectionsAddRepo , RepoCollectionsRemoveRepo repoCollectionsRemoveRepo
    , RepoCollectionRename repoCollectionRename) {
        this.repoCollectionsCreate = repoCollectionsCreate;
        this.repoCollectionGet = repoCollectionGet;
        this.repoCollectionsAddRepo = repoCollectionsAddRepo;
        this.repoCollectionsRemoveRepo = repoCollectionsRemoveRepo;
        this.repoCollectionRename = repoCollectionRename;
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

    @DeleteMapping("/removeRepoFromCollection/{collectionName}")
    public void removeRepoFromCollection(@PathVariable String collectionName) {
        repoCollectionsRemoveRepo.removeRepoFromCollection(collectionName);
    }

    @GetMapping("/{oldName}/{newName}")
    public void renameRepo(@PathVariable String oldName, @PathVariable String newName) {
        repoCollectionRename.renameRepoCollection(oldName,newName);
    }
}
