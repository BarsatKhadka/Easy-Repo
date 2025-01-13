package com.Barsat.Github.Repository.Management.Service.FeatureService;

import com.Barsat.Github.Repository.Management.DTO.FeatureDTO;
import com.Barsat.Github.Repository.Management.Models.FeatureRequest.FeatureRequestEntity;
import com.Barsat.Github.Repository.Management.Repository.FeatureRequestRepository;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {

    private final FeatureRequestRepository featureRequestRepository;
    private final GetAuthenticatedUserName getAuthenticatedUserName;
    public FeatureService(FeatureRequestRepository featureRequestRepository , GetAuthenticatedUserName getAuthenticatedUserName) {
        this.featureRequestRepository = featureRequestRepository;
        this.getAuthenticatedUserName = getAuthenticatedUserName;
    }

    public String processFeature(FeatureDTO featureDTO) {
        FeatureRequestEntity featureRequestEntity = new FeatureRequestEntity();
        featureRequestEntity.setFeatureDescription(featureDTO.getDescription());
        featureRequestEntity.setUserName(getAuthenticatedUserName.getUsername());
        featureRequestRepository.save(featureRequestEntity);
        return "success";


    }
}
