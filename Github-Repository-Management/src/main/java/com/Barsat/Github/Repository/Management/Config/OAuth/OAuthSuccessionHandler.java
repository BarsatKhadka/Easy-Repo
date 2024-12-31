package com.Barsat.Github.Repository.Management.Config.OAuth;

import com.Barsat.Github.Repository.Management.Config.Jwt.JwtUtils;
import com.Barsat.Github.Repository.Management.Models.Provider;
import com.Barsat.Github.Repository.Management.Models.RepoModels.GithubRepoEntity;
import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import com.Barsat.Github.Repository.Management.Service.CommitGraph.CommitGraphService;
import com.Barsat.Github.Repository.Management.Service.GithubFetchService.GithubFetchSaveService;
import com.Barsat.Github.Repository.Management.Service.OAuthService.OAuthService;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionsService;
import com.Barsat.Github.Repository.Management.Service.UtilityService.NotifyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class OAuthSuccessionHandler implements AuthenticationSuccessHandler {

    //field injection is not reccomended so using constructor injection
    private final UserRepo userRepo;
    private final OAuthService oAuthService;
    private final RepoCollectionsService repoCollectionsService;
    private final GithubFetchSaveService githubFetchSaveService;
    private final JwtUtils jwtUtils;
    private final CommitGraphService commitGraphService;
    private final NotifyService notifyService;

    public OAuthSuccessionHandler(UserRepo userRepo,
                                  OAuthService oAuthService,
                                  RepoCollectionsService repoCollectionsService,
                                  GithubFetchSaveService githubFetchSaveService,
                                  JwtUtils jwtUtils,
                                  CommitGraphService commitGraphService,
                                  NotifyService notifyService) {
        this.userRepo = userRepo;
        this.oAuthService = oAuthService;
        this.repoCollectionsService = repoCollectionsService;
        this.githubFetchSaveService = githubFetchSaveService;
        this.jwtUtils = jwtUtils;
        this.commitGraphService = commitGraphService;
        this.notifyService = notifyService;
    }



    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String clientId;

    private String userName;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        DefaultOAuth2User oauth2User = (DefaultOAuth2User) authentication.getPrincipal();

        OAuth2AuthenticationToken oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;



        //getting the code from callback URL and setting it in custom defined OAuthService in case of future use.
        String code = (request.getParameter("code"));
        oAuthService.setCode(code);

        //generating setting and getting access token in OAuthService custom defined class. I need to pass generate because the Token is here and not in the oAuthService.
        oAuthService.generateAccessToken(oauth2AuthenticationToken, "github");
        String accessToken = oAuthService.getAccessToken();
        oAuthService.setAccessToken(accessToken);
        System.out.println(accessToken);

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



        //registering github user to my custom user model
        TheUser githubUser = new TheUser();
        githubUser.setUsername(name);
        githubUser.setEmail(email);
        githubUser.setAvatarUrl(avatar_url);
        githubUser.setBio(bio);
        githubUser.setPassword(new BCryptPasswordEncoder().encode("Password"));
        githubUser.setProviderUserId(id);
        githubUser.setProvider(Provider.GITHUB);


//        Enable this only when needed. Enbaling this allows oAuth users to login through normal sign in.(like when authenticating through postman)
        githubUser.setEnabled(true);


        //save github user , if there is no email assosciated to it.
        if(!userRepo.existsByEmail(email)) {
            userRepo.save(githubUser);
        }

        //giving githubFetchService username and accessToken to access the repositories
        //also do it only after saving the user because if you do it before saving the user it will return null the first login hence it won't be mapped at first login.
        githubFetchSaveService.fetchSaveRepositories(name,accessToken);
        repoCollectionsService.setUsername(name);

        //all collection is made as soon as user is authenticated.
        repoCollectionsService.allCollection();




        String jwtToken = jwtUtils.generateToken(userName);
        response.setHeader("Authorization", "Bearer " + jwtToken);

        //redirect to the url after approved
        new DefaultRedirectStrategy().sendRedirect(request, response, "http://localhost:5173");

    }



}
