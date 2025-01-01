package com.Barsat.Github.Repository.Management.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LinesOfCodeDTO {

    private String language;
    private String linesOfCode;
    private String blankLines;
    private String commentLines;
    private String files;
    private String totalLines;
}
