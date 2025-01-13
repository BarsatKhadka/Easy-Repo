package com.Barsat.Github.Repository.Management.Repository;

import com.Barsat.Github.Repository.Management.Models.FeatureRequest.FeatureRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRequestRepository extends JpaRepository<FeatureRequestEntity, Integer> {
}
