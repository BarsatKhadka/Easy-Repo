package com.Barsat.Github.Repository.Management.Service;

import com.Barsat.Github.Repository.Management.Config.Jwt.JwtUtils;
import com.Barsat.Github.Repository.Management.Models.Provider;
import com.Barsat.Github.Repository.Management.Models.RequestModels.LoginRequest;
import com.Barsat.Github.Repository.Management.Models.RequestModels.SignUpRequest;
import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import com.Barsat.Github.Repository.Management.Service.RepoCollectionsService.RepoCollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RepoCollectionsService repoCollectionsService;

    public ResponseEntity<TheUser> register(SignUpRequest signUpRequest) {

        //creating a new user instance with the information of signup request.
        TheUser newUser = new TheUser(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());

        //get the normal password and encode it by Bcrypt before sending it to database
        newUser.setPassword(encoder.encode(signUpRequest.getPassword()));

        //pre defined values for registered users.
        newUser.setEnabled(true);
        newUser.setProvider(Provider.SELF);
        newUser.setProviderUserId(UUID.randomUUID().toString());
        newUser.setEmailVerified(false);
        newUser.setBio("");
        newUser.setAvatarUrl("");

        return new ResponseEntity<> (userRepo.save(newUser) , HttpStatus.CREATED);
    }


    public String loginVerify(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));



        //generate jwt token if authenticated
        if(authentication.isAuthenticated()) {
            //repoCollectionsServices need username. If the user is logging in from custom login , set it from here.
            repoCollectionsService.setUsername(loginRequest.getUsername());
            return jwtUtils.generateToken(loginRequest.getUsername());
        }


        return "Invalid username or password";


    }
}
