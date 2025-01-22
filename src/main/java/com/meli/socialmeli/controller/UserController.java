package com.meli.socialmeli.controller;

import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<?> listFollowersBySeller(@PathVariable(name="userId") Integer sellerId){
        return new ResponseEntity<>(userService.findFollowersBySeller(sellerId), HttpStatus.OK);

    }
}
