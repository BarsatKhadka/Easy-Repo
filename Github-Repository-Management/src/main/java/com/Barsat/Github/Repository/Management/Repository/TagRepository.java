package com.Barsat.Github.Repository.Management.Repository;

import com.Barsat.Github.Repository.Management.Models.RepoModels.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

}
