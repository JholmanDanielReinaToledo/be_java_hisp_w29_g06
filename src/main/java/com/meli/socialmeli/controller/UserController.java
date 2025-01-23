package com.meli.socialmeli.controller;


import com.meli.socialmeli.service.ISellerService;
import com.meli.socialmeli.service.SellerServiceImpl;
import com.meli.socialmeli.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.meli.socialmeli.service.IUserService;

import com.meli.socialmeli.dto.ResponseDto;

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

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> listFollowersBySeller(@PathVariable(name="userId") Integer sellerId,
                                                   @RequestParam(required=false, defaultValue = "name_asc") String order){
        return new ResponseEntity<>(sellerService.findFollowersBySeller(sellerId), HttpStatus.OK);
    }

    @GetMapping("{userId}/followed/list")
    public ResponseEntity<?> listFollowed(@PathVariable Integer userId,
                                          @RequestParam(required = false, defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(userService.getFollowedList(userId, order), HttpStatus.OK);
    }

}
