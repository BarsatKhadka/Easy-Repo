package com.Barsat.Github.Repository.Management.Models.ResponseModels;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResposne {

    String jwtToken;
    String message;
}
