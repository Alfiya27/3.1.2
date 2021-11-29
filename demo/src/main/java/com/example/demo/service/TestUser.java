package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestUser {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TestUser(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createUser() {

        roleService.addRole(new Role("ROLE_ADMIN"));
        roleService.addRole(new Role("ROLE_USER"));

        Set<Role> role1 = new HashSet<>();
        role1.add(roleService.findByRoleName("ROLE_USER"));
        role1.add(roleService.findByRoleName("ROLE_ADMIN"));

        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword(passwordEncoder.encode("admin"));
        user1.setRoles(role1);
        userService.addUser(user1);

        Set<Role> role2 = new HashSet<>();
        role2.add(roleService.findByRoleName("ROLE_USER"));

        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword(passwordEncoder.encode("user"));
        user2.setRoles(role2);
        userService.addUser(user2);
    }
}
