package com.Barsat.Github.Repository.Management.Service;

import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    public TheUser register(TheUser theUser) {
        return userRepo.save(theUser);
    }
}
