package com.Barsat.Github.Repository.Management.Config.OAuth;

import com.Barsat.Github.Repository.Management.Models.Provider;
import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class OAuthSuccessionHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OAuthService oAuthService;

    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String clientId;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        DefaultOAuth2User oauth2User = (DefaultOAuth2User) authentication.getPrincipal();

//        allows you to look at all attributes that is coming from the user
        Map<String, Object> attributes = oauth2User.getAttributes();
        attributes.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });

        String email = oauth2User.getAttribute("email").toString();
        String name = oauth2User.getAttribute("name").toString();
        String avatar_url = oauth2User.getAttribute("avatar_url").toString();
        String bio = oauth2User.getAttribute("bio").toString();
        String id = oauth2User.getAttribute("id").toString();


        //getting the code to get access token from the request. This is passed in callback url by github after authorizing user.
        String code = (request.getParameter("code"));
        String accessToken = oAuthService.generateAccessToken(code);
        System.out.println(accessToken);



        TheUser githubUser = new TheUser();
        githubUser.setUsername(name);
        githubUser.setEmail(email);
        githubUser.setAvatarUrl(avatar_url);
        githubUser.setBio(bio);
        githubUser.setPassword(new BCryptPasswordEncoder().encode("Password"));
        githubUser.setProviderUserId(id);
        githubUser.setProvider(Provider.GITHUB);

//        githubUser.setEnabled(true);   Enable this only when needed.


        //save github user , if there is no email assosciated to it.
        if(!userRepo.existsByEmail(email)) {
            userRepo.save(githubUser);
        }




        //redirect to the url after approved
        new DefaultRedirectStrategy().sendRedirect(request, response, "/hello");

    }


}
