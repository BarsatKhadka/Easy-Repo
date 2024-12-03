package com.Barsat.Github.Repository.Management.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TheUserSecurityDetails implements UserDetails {

    private TheUser theUser;

    public TheUserSecurityDetails(TheUser user) {
        this.theUser = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return theUser.getPassword();
    }

    public String getEmail(){
        return theUser.getEmail();
    }

    @Override
    public String getUsername() {
        return theUser.getUsername();
    }

    //these all are determined by if user is enabled
    @Override
    public boolean isAccountNonExpired() {
        return theUser.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return theUser.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return theUser.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return theUser.isEnabled();
    }
}
