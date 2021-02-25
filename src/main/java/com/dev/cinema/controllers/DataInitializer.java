package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";
    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void addData() {
        Role roleAdmin = new Role();
        roleAdmin.setRoleName(ROLE_ADMIN);
        roleService.add(roleAdmin);
        Role roleUser = new Role();
        roleUser.setRoleName(ROLE_USER);
        roleService.add(roleUser);
        User userAdmin = new User();
        userAdmin.setEmail("admin");
        userAdmin.setPassword("123456");
        userAdmin.setRoles(Set.of(roleAdmin,roleUser));
        userService.add(userAdmin);
    }
}
