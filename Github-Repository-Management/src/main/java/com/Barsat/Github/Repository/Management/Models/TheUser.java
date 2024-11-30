package com.Barsat.Github.Repository.Management.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String username;

    private String email;

    private String password;

    public TheUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public TheUser(String username, String password) {
        this.username = username;
        this.email = password;
    }

    public TheUser() {

    }
}
