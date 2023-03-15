package com.ryzhov_andrei.spring_security_rest_api_app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Entity
@Table(name = "roles")
@Data
@ToString
public class Role extends BaseEntity {

    @Column(name = "name")
    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}
