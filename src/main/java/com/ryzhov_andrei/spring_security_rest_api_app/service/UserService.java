package com.ryzhov_andrei.spring_security_rest_api_app.service;

import com.ryzhov_andrei.spring_security_rest_api_app.model.User;

public interface UserService extends GenericService<User, Long> {
    User create(User user);

    User update(User user);

    User findByUsername(String username);
}

