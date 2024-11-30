package com.Barsat.Github.Repository.Management.Controller;

import com.Barsat.Github.Repository.Management.Models.RequestModels.LoginRequest;
import com.Barsat.Github.Repository.Management.Models.RequestModels.SignUpRequest;
import com.Barsat.Github.Repository.Management.Repository.UserRepo;
import com.Barsat.Github.Repository.Management.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/register")
    public ResponseEntity<String>  register(@Valid @RequestBody SignUpRequest signUpRequest) {

        if(userRepo.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists in the database.");
        }

        if(userRepo.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists in the database.");
        }

        authService.register(signUpRequest);

        return ResponseEntity.ok("You are registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        if(userRepo.existsByUsername(loginRequest.getUsername())) {
             authService.loginVerify(loginRequest);
             return ResponseEntity.ok("You are logged in successfully");
        }

        return ResponseEntity.badRequest().body("Invalid username or password.");

    }



}
