package com.meli.socialmeli.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meli.socialmeli.dto.NumberOfProductsInSaleDto;
import com.meli.socialmeli.dto.request.PostDto;
import com.meli.socialmeli.dto.response.ResponseWrapperDto;
import com.meli.socialmeli.service.IPostService;
import com.meli.socialmeli.service.PostServiceImpl;


@RestController
@RequestMapping("/products")
public class PostController {

    private IPostService postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<ResponseWrapperDto> addPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(
                postService.addPost(postDto),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/promo-post")
    public ResponseEntity<ResponseWrapperDto> createPromoPost(@RequestBody PostDto promoPostDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                postService.createPromoPost(promoPostDto)
        );
    }


    @GetMapping("/promo-post/count")
    public ResponseEntity<?> getNumberOfProductsInSaleOfSeller(@RequestParam(name="user_id") Integer userId) {
        NumberOfProductsInSaleDto dto = postService.getNumberOfProductsInSale(userId);
        return new ResponseEntity<NumberOfProductsInSaleDto>(dto, HttpStatus.OK);
    }

    @GetMapping("/promo-post/list?user_id={userId}")
    public ResponseEntity<?> listAllProductsInSaleOfSeller(@PathVariable Integer userId) {
        return new ResponseEntity<>(postService.listAllProductsInSaleOfSeller(userId), HttpStatus.OK);
    }


}
