package com.ryzhov_andrei.spring_security_rest_api_app.security.jwt;

import com.ryzhov_andrei.spring_security_rest_api_app.model.Role;
import com.ryzhov_andrei.spring_security_rest_api_app.model.Status;
import com.ryzhov_andrei.spring_security_rest_api_app.model.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                user.getRole().getAuthorities()
//                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))

        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getPermissions().toString())
                ).collect(Collectors.toList());
    }
}
