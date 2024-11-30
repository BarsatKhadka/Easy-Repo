package com.Barsat.Github.Repository.Management.Config.Jwt;

import com.Barsat.Github.Repository.Management.Service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.context.ApplicationContextAware;
import org.springframework.beans.BeansException;


import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MyUserDetailsService myUserDetailsService;



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
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

            //If this is true , then user can be authenticated.
            if(jwtUtils.validateToken(token,userDetails)){

                //if user has valid jwt , make him a username password authentication token to pass , this replaces the traditional login he needs to do manually
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //add request details to authToken
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //give securityContextHolder the auth Token and it sets the authentication as authToken. Now it will no longer
                //redirect to login form because it already got username password authentication token which it would get from a form
                SecurityContextHolder.getContext().setAuthentication(authToken);


            }

        }


        //continue your filter work.
        filterChain.doFilter(request, response);




    }
}
