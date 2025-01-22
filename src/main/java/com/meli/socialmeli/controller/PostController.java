package com.meli.socialmeli.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meli.socialmeli.dto.NumberOfProductsInSaleDto;
import com.meli.socialmeli.service.IPostService;
import com.meli.socialmeli.service.PostServiceImpl;


@RestController
@RequestMapping("/products")
public class PostController {

    private IPostService postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/promo-post/count")
    public ResponseEntity<?> getNumberOfProductsInSaleOfSeller(@RequestParam(name="user_id") Integer userId) {
        NumberOfProductsInSaleDto dto = postService.getNumberOfProductsInSale(userId);
        return new ResponseEntity<NumberOfProductsInSaleDto>(dto, HttpStatus.OK);
    }
    
}
