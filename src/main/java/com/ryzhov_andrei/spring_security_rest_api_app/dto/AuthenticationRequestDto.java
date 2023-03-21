package com.ryzhov_andrei.spring_security_rest_api_app.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
