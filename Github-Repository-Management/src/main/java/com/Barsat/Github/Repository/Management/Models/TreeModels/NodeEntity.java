package com.Barsat.Github.Repository.Management.Models.TreeModels;

import com.Barsat.Github.Repository.Management.Nodes.Node;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class NodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    public boolean isDirectory;

    //since there can be only one parent , merge a parent id in the table for each entity
    @ManyToOne
    @JoinColumn(name = "parent_id")
    public NodeEntity parent;

    public String path;
    public String displayName;
    public String url;

    @OneToMany(mappedBy = "parent")
    public Set<NodeEntity> children = new HashSet<>();



}
