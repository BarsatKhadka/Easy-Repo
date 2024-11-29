package com.Barsat.Github.Repository.Management.Config.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils = new JwtUtils();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //when token is sent it will sent on Bearer type , in format : Bearer + Token.

        //get authHeader Authorization where the token is
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            //cut the Bearer thing and get pure token
            token = authHeader.substring(7);
            username = jwtUtils.extractUsername(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        }




    }
}
