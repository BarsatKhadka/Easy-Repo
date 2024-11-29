package com.Barsat.Github.Repository.Management.Models;

import jakarta.persistence.Entity;
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
    private Integer id;

    private String username;

    private String password;

}
