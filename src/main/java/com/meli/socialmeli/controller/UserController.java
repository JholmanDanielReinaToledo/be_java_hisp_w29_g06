package com.meli.socialmeli.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.socialmeli.dto.ResponseDto;
import com.meli.socialmeli.service.ISellerService;
import com.meli.socialmeli.service.IUserService;
import com.meli.socialmeli.service.SellerServiceImpl;
import com.meli.socialmeli.service.UserServiceImpl;

@RequestMapping("/users")
@RestController
public class UserController {

    private IUserService userService;
    private ISellerService sellerService;

    public UserController(UserServiceImpl userService, SellerServiceImpl sellerService) {
        this.userService = userService;
        this.sellerService = sellerService;
    }

    @PostMapping("/{userId}/follow/{sellerId}")
    public ResponseEntity<ResponseDto> followSeller(@PathVariable Integer userId, @PathVariable Integer sellerId ) {
        ResponseDto response = this.userService.followSeller(userId, sellerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<ResponseDto> unfollowSeller(@PathVariable Integer userId, @PathVariable(name="userIdToUnfollow") Integer sellerId) {
        ResponseDto response = this.userService.unfollowSeller(userId, sellerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> listFollowersBySeller(@PathVariable(name="userId") Integer sellerId){
        return new ResponseEntity<>(sellerService.findFollowersBySeller(sellerId), HttpStatus.OK);
    }

    @GetMapping("{userId}/followed/list")
    public ResponseEntity<?> listFollowed(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getFollowedList(userId),HttpStatus.OK);
    }


}
