package com.Barsat.Github.Repository.Management.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
public class TheUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", nullable = false)  //username can't be null and should be unique
    private String username;

    @Column(name = "user_email" ,unique = true)
    private String email;

    private String password;

    @Column(length = 10000)
    private String avatarUrl;

    private String bio ;

    private boolean isEnabled;

    private boolean emailVerified;

    //providers including self or github.
    private Provider provider;
    private String providerUserId;

    public TheUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public TheUser() {

    }

    //add more fields if needed.
}
