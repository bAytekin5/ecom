package com.berkay.ecom.app.controller;


import com.berkay.ecom.app.model.User;
import com.berkay.ecom.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.fetchAllUsers();
    }

    @PostMapping("/users")
    public List<User> createUser(@RequestBody User user) {
        userService.addUser(user);
        log.info(user.toString() + " created user" + user.toString());
        return userService.fetchAllUsers();
    }
}
