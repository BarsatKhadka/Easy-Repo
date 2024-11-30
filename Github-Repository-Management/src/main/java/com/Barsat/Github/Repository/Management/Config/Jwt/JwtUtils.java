package com.Barsat.Github.Repository.Management.Config.Jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtils {


    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

//        return Jwts.builder()
//                .claims()
//                .add(claims)
        return "Hello";
    }






    public String extractUsername(String token) {
        return "this is my username";
    }
}
