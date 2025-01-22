package com.meli.socialmeli.controller;

import org.springframework.web.bind.annotation.RestController;

import com.meli.socialmeli.service.IUserService;

@RestController
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    
}
