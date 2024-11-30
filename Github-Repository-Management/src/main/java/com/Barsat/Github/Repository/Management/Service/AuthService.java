package com.Barsat.Github.Repository.Management.Service;

import com.Barsat.Github.Repository.Management.Config.Jwt.JwtUtils;
import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    AuthenticationManager authenticationManager;

    public TheUser register(TheUser theUser) {

        //get the normal password and encode it by Bcrypt before sending it to database
        theUser.setPassword(encoder.encode(theUser.getPassword()));
        return userRepo.save(theUser);
    }


    public String loginVerify(TheUser theUser) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(theUser.getUsername(), theUser.getPassword()));

        //generate jwt token if authenticated
        if(authentication.isAuthenticated()) {
            return jwtUtils.generateToken(theUser.getUsername());
        }


        return "Invalid username or password";


    }
}
