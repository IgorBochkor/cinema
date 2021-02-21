package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

@Controller
public class InjectDataController {
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";
    private final UserService userService;
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;

    public InjectDataController(UserService userService,
                                RoleService roleService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
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
        userAdmin.setRoles(List.of(roleAdmin,roleUser));
        userService.add(userAdmin);
        shoppingCartService.registerNewShoppingCart(userAdmin);
    }
}
