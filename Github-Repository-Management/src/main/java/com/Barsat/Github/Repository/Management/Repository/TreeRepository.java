package com.Barsat.Github.Repository.Management.Repository;

import com.Barsat.Github.Repository.Management.Models.TreeModels.TreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<TreeEntity, Integer> {
}
