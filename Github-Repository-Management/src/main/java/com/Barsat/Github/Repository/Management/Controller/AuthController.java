package com.Barsat.Github.Repository.Management.Controller;

import com.Barsat.Github.Repository.Management.Models.TheUser;
import com.Barsat.Github.Repository.Management.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/register")
    public TheUser register(@RequestBody TheUser theUser) {
        return authService.register(theUser);
    }

    @PostMapping("/login")
    public String login(@RequestBody TheUser theUser) {
        return authService.loginVerify(theUser);
    }



}
