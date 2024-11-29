package com.Barsat.Github.Repository.Management.Config.Jwt;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {


    public String extractUsername(String token) {
        return "this is my username";
    }
}
