package com.Barsat.Github.Repository.Management.Config.OAuth;

import com.Barsat.Github.Repository.Management.Models.TheUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class OAuthSuccessionHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        DefaultOAuth2User oauth2User = (DefaultOAuth2User) authentication.getPrincipal();

        Map<String, Object> attributes = oauth2User.getAttributes();
        attributes.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });

        String email = oauth2User.getAttribute("email").toString();
        String name = oauth2User.getAttribute("name").toString();
        String avatar_url = oauth2User.getAttribute("avatar_url").toString();
        String bio = oauth2User.getAttribute("bio").toString();
        System.out.println(email);

        TheUser githubUser = new TheUser(name, email, avatar_url,bio);




        //redirect to the url after approved
        new DefaultRedirectStrategy().sendRedirect(request, response, "/hello");

    }
}
