package com.Barsat.Github.Repository.Management.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/easyrepo/user")
public class UserExposeController {

    @GetMapping
    public Map<String,Object> getUser(@AuthenticationPrincipal OAuth2User principal) {

        return Map.of(
                "username: ", principal.getAttribute("name") ,
                "email: ", principal.getAttribute("email"));

    }
}
