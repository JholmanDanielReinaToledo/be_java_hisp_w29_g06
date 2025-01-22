package com.meli.socialmeli.controller;


import com.meli.socialmeli.service.ISellerService;
import com.meli.socialmeli.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.meli.socialmeli.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.socialmeli.dto.ResponseDto;

@RequestMapping("/users/")
@RestController
public class UserController {

    private IUserService userService;
    private ISellerService sellerService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{sellerId}")
    public ResponseEntity<ResponseDto> followSeller(@PathVariable Integer userId, @PathVariable Integer sellerId ) {
        ResponseDto response = this.userService.followSeller(userId, sellerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<?> listFollowersBySeller(@PathVariable(name="userId") Integer sellerId){
        return new ResponseEntity<>(sellerService.findFollowersBySeller(sellerId), HttpStatus.OK);

    }
}
