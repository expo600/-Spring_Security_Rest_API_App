package com.ryzhov_andrei.spring_security_rest_api_app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    USERS_DELETE("users:delete"),

    EVENTS_READ("events:read"),
    EVENTS_WRITE("events:write"),
    EVENTS_DELETE("events:delete"),

    FILES_READ("files:read"),
    FILES_WRITE("files:write"),
    FILES_DELETE("files:delete");

    private final String permission;

}
