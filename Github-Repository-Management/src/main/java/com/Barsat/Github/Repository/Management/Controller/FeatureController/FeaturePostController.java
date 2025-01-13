package com.Barsat.Github.Repository.Management.Controller.FeatureController;

import com.Barsat.Github.Repository.Management.DTO.FeatureDTO;
import com.Barsat.Github.Repository.Management.Service.FeatureService.FeatureService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeaturePostController {

    private final FeatureService featureService;
    public FeaturePostController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @PostMapping("/easyrepo/post/feature")
    public String postFeature(@RequestBody FeatureDTO featureDTO) {
        return featureService.processFeature(featureDTO);
    }
}
