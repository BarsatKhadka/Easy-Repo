package com.Barsat.Github.Repository.Management.Models.FeatureRequest;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FeatureRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer FeatureId;

    @Lob
    private String FeatureDescription;

    private String userName;
}
