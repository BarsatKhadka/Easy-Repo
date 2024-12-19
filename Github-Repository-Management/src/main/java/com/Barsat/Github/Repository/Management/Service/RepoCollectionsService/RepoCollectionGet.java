package com.Barsat.Github.Repository.Management.Service.RepoCollectionsService;

import com.Barsat.Github.Repository.Management.Models.RepoModels.RepoCollectionsEntity;
import com.Barsat.Github.Repository.Management.Repository.RepoCollectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class RepoCollectionGet {

    @Autowired
    private RepoCollectionsRepository repoCollectionsRepository;

    /*here is the tricky part , we don't do find all here because i already have done that find all github repos and made a collection (you can see it in
    oAuth succession Handler. So i am going to call that repoCollectionEntity of all collections i had made */
    public RepoCollectionsEntity allRepoCollections(){

        //getting the username so that i don't extract other's all repositories collection.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;

        if(authentication != null && authentication.getPrincipal() instanceof OAuth2User){
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            username = oauth2User.getAttribute("name");
            System.out.println(username);

        }

        else{

            username = authentication.getName();

        }



        RepoCollectionsEntity allRepoCollectionsEntity = repoCollectionsRepository.findByMasterUserUsernameAndCollectionName(username, "All Repositories");
        return allRepoCollectionsEntity;
    }


}
