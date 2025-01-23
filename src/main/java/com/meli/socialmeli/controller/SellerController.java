package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.SellerDto;
import com.meli.socialmeli.service.ISellerService;
import com.meli.socialmeli.service.SellerServiceImpl;
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

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<SellerDto> countFollowers(@PathVariable Integer userId) {
        SellerDto sellerDto = sellerService.countFollowers(userId);
        return new ResponseEntity<>(sellerDto, HttpStatus.OK);
    }
}
