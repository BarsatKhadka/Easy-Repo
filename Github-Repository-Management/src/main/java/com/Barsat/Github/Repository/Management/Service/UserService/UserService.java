package com.Barsat.Github.Repository.Management.Service.UserService;

import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final GetAuthenticatedUserName getAuthenticatedUserName;
    private final UserRepo userRepo;

    public UserService(GetAuthenticatedUserName getAuthenticatedUserName , UserRepo userRepo) {
        this.getAuthenticatedUserName = getAuthenticatedUserName;
        this.userRepo = userRepo;
    }
    public TheUser getUser() {
        String username = getAuthenticatedUserName.getUsername();
        TheUser user = userRepo.findByUsername(username);
        return user;
    }
}
