package com.Barsat.Github.Repository.Management.Service;

import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public TheUser register(TheUser theUser) {

        //get the normal password and encode it by Bcrypt before sending it to database
        theUser.setPassword(encoder.encode(theUser.getPassword()));


        return userRepo.save(theUser);
    }


}
