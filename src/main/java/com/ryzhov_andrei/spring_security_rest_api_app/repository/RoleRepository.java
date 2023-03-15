package com.ryzhov_andrei.spring_security_rest_api_app.repository;

import com.ryzhov_andrei.spring_security_rest_api_app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String name);
}
