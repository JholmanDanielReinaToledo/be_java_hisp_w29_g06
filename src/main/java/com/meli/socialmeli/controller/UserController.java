package com.meli.socialmeli.controller;

import com.meli.socialmeli.service.IUserService;
import com.meli.socialmeli.service.UserServiceImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private IUserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
}
