package com.Barsat.Github.Repository.Management.Service.UtilityService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class GetAuthenticatedUserName {

    public String getUsername(){

        //get the username and find the user to set the user in collections record.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;

        if(authentication != null && authentication.getPrincipal() instanceof OAuth2User){
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            username = oauth2User.getAttribute("name");
        }

        else{

            username = authentication.getName();

        }
        return username;
    }

}
