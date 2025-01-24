package com.meli.socialmeli.controller;

import com.meli.socialmeli.constants.Endpoints;
import com.meli.socialmeli.dto.SellerDto;
import com.meli.socialmeli.service.ISellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerController {

    private ISellerService sellerService;

    public SellerController(ISellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping(Endpoints.USERS_FOLLOWERS_COUNT)
    public ResponseEntity<SellerDto> countFollowers(@PathVariable Integer userId) {
        return new ResponseEntity<>(sellerService.countFollowers(userId), HttpStatus.OK);
    }
}
