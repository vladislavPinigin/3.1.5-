package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initDbUsers() {
        Set<Role> userRole = new HashSet<>();
        Set<Role> adminRole = new HashSet<>();
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.saveRole(roleUser);
        roleService.saveRole(roleAdmin);
        userRole.add(roleUser);
        adminRole.add(roleUser);
        adminRole.add(roleAdmin);

        User admin = new User();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirstName("Вася");
        admin.setLastName("Васячкин");
        admin.setAge((byte) 30);
        admin.setRoles(adminRole);
        userService.saveUser(admin);

        User user = new User();
        user.setId(2L);
        user.setUsername("user");
        user.setPassword("user");
        user.setFirstName("Петя");
        user.setLastName("Петкин");
        user.setAge((byte) 20);
        user.setRoles(userRole);
        userService.saveUser(user);
    }
}