package com.Barsat.Github.Repository.Management.Models.RequestModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginRequest {

    private String username;

    private String password;


}
