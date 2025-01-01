package com.Barsat.Github.Repository.Management.Service.Insights;

import com.Barsat.Github.Repository.Management.DTO.LinesOfCodeDTO;
import com.Barsat.Github.Repository.Management.Models.ResponseModels.LOCResponseModel;
import com.Barsat.Github.Repository.Management.Service.UtilityService.GetAuthenticatedUserName;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;

@Service
public class GithubRepoInsightService {

    private final GetAuthenticatedUserName getAuthenticatedUserName;

    public GithubRepoInsightService(GetAuthenticatedUserName getAuthenticatedUserName) {
        this.getAuthenticatedUserName = getAuthenticatedUserName;
    }


    public List<LinesOfCodeDTO> getTotalLinesOfCode(String repoName){

        String username = getAuthenticatedUserName.getUsername();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://api.codetabs.com/v1/loc?github=" + username + "/" + repoName;

        List<LinesOfCodeDTO> linesOfCodeDTOS = new ArrayList<>();

        ResponseEntity<LOCResponseModel[]> response = restTemplate.exchange(url, HttpMethod.GET,entity,LOCResponseModel[]   .class);
        for(LOCResponseModel locResponseModel : response.getBody()){
            LinesOfCodeDTO lr = new LinesOfCodeDTO(locResponseModel.getLanguages(), locResponseModel.getLinesOfCode(),
                                                    locResponseModel.getBlankLines() , locResponseModel.getComments(), locResponseModel.getFiles(),locResponseModel.getTotalLines());
            linesOfCodeDTOS.add(lr);
        }
        return linesOfCodeDTOS;



    }
}
