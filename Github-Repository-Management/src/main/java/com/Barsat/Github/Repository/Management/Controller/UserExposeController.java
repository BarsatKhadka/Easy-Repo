package com.Barsat.Github.Repository.Management.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/easyrepo/user")
public class UserExposeController {

    @GetMapping("/isAuthenticated")
    public ResponseEntity<Map<String , Boolean>> isAuthenticated(Authentication authentication) {
        boolean isAuthenticated = authentication != null  && authentication.isAuthenticated() ;
        if (isAuthenticated) {
            System.out.println("isAuthenticated");
            return ResponseEntity.ok(Map.of("isAuthenticated", true));
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("isAuthenticated", false));
        }

    }

    @GetMapping
    public Map<String,Object> getUser(@AuthenticationPrincipal OAuth2User principal) {

        return Map.of(
                "username: ", principal.getAttribute("name") ,
                "email: ", principal.getAttribute("email"));

    }
}
