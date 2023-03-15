package com.ryzhov_andrei.spring_security_rest_api_app.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg,Throwable t) {
        super(msg, t);
    }
    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
