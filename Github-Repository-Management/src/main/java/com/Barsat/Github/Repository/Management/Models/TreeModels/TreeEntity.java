package com.Barsat.Github.Repository.Management.Models.TreeModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TreeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer treeId;


}
