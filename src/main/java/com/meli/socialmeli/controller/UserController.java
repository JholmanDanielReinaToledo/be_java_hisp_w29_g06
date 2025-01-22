package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.SellerDto;
import com.meli.socialmeli.service.IUserService;
import com.meli.socialmeli.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private IUserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerDto> countFollowers(@PathVariable Integer userId) {
        SellerDto sellerDto = userService.countFollowers(userId);
        return new ResponseEntity<SellerDto>(sellerDto, HttpStatus.OK);
    }


}
