package com.Barsat.Github.Repository.Management.Controller.OpenVSCode;

import com.Barsat.Github.Repository.Management.Service.OpenVsCode.OpenVsCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("easyrepo/openVSCode")
public class OpenVSCodeController {

    private final OpenVsCodeService openVsCodeService;

    public OpenVSCodeController(OpenVsCodeService openVsCodeService) {
        this.openVsCodeService = openVsCodeService;
    }

    @GetMapping("/{repoName}")
    public String openVSCode(@PathVariable String repoName){
        return openVsCodeService.openVsCodeUrl(repoName);
    }


}
