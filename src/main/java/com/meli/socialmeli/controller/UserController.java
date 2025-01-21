package com.meli.socialmeli.controller;

import com.meli.socialmeli.service.IUserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }
}
