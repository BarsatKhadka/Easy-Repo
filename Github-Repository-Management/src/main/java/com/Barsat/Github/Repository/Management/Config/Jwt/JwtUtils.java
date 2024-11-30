package com.Barsat.Github.Repository.Management.Config.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


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

        //building the jwts to return
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


    //get Key , logic is divided into constructor and this for the key.
    private SecretKey getMykey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }



    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getMykey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
