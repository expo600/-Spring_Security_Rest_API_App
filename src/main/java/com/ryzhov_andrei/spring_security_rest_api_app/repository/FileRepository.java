package com.ryzhov_andrei.spring_security_rest_api_app.repository;

import com.ryzhov_andrei.spring_security_rest_api_app.model.File;
import com.ryzhov_andrei.spring_security_rest_api_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {
}
