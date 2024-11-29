package com.Barsat.Github.Repository.Management.Controller;

import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }



}
