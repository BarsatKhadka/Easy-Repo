package com.Barsat.Github.Repository.Management.Config.Jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtUtils {


    private String secretKey = "";

    public JwtUtils()  {
        try {
            KeyGenerator keyType = KeyGenerator.getInstance("HmacSHA256");
            SecretKey Key = keyType.generateKey();
            secretKey = Base64.getEncoder().encodeToString(Key.getEncoded());

        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }


    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        //building the jwts type
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 *60 *30))
                .and()
                .signWith(getMykey())
                .compact();


    }



    private Key getMykey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }


    public String extractUsername(String token) {
        return "this is my username";
    }
}
