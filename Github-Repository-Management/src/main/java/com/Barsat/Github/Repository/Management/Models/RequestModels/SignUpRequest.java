package com.Barsat.Github.Repository.Management.Models.RequestModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Data
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank
    @Size(max=253)
    private String email;

    @NotBlank
    @Size(min = 6, max = 50)
    private String password;
}
