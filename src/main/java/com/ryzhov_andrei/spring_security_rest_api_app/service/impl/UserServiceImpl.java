package com.ryzhov_andrei.spring_security_rest_api_app.service.impl;

import com.ryzhov_andrei.spring_security_rest_api_app.model.Role;
import com.ryzhov_andrei.spring_security_rest_api_app.model.Status;
import com.ryzhov_andrei.spring_security_rest_api_app.model.User;
import com.ryzhov_andrei.spring_security_rest_api_app.repository.RoleRepository;
import com.ryzhov_andrei.spring_security_rest_api_app.repository.UserRepository;
import com.ryzhov_andrei.spring_security_rest_api_app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("FindById - no User find by id: {}", id);
        }
        log.info("Find User by id: {}", id);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("GetAll:  {} Users found", users.size());
        return users;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.warn("FindByName - no User find by name: {}", username);
        }
        log.info("Find User by name: {}", username);
        return user;
    }

    @Override
    public User create(User user) {
        Role roleUser = roleRepository.findByRoleName("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        log.info("Create - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public User update(User user) {
        log.info("Update - User: {}", user);
        user.setStatus(Status.ACTIVE);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("DeleteById - no User find by id: {}", id);
        } else {
            log.info("Delete User by id: {}", id);
            user.setStatus(Status.DELETED);
            userRepository.save(user);
        }
    }
}