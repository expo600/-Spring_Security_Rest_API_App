package com.ryzhov_andrei.spring_security_rest_api_app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(
            Permission.EVENTS_READ,
            Permission.FILES_READ
    )),
    MODERATOR(Set.of(
            Permission.USERS_READ,
            Permission.EVENTS_READ, Permission.EVENTS_WRITE,
            Permission.FILES_READ, Permission.FILES_WRITE
    )),
    ADMIN(Set.of(
            Permission.USERS_READ, Permission.USERS_WRITE,
            Permission.EVENTS_READ, Permission.EVENTS_WRITE,
            Permission.FILES_READ, Permission.FILES_WRITE
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
