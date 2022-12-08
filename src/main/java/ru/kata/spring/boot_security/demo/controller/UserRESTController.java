package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserRESTController {
    private final UserService userService;


    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUser(Principal principal) {
        Long id = userService.getUserByUsername(principal.getName()).getId();
        return userService.getUserById(id);
    }
}
