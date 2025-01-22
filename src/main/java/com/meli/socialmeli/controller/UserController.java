package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.ResponseDto;
import com.meli.socialmeli.service.IUserService;
import com.meli.socialmeli.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private IUserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<ResponseDto> followSeller(@PathVariable Integer userId, @PathVariable Integer userIdToFollow ) {
        ResponseDto response = this.userService.followSeller(userId, userIdToFollow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
