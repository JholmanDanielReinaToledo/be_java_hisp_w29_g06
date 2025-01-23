package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.NumberOfProductsInSaleDto;
import com.meli.socialmeli.dto.response.PostFromFollowedDto;
import com.meli.socialmeli.dto.response.ResponseWrapperDto;
import com.meli.socialmeli.service.IPostService;
import com.meli.socialmeli.service.PostServiceImpl;
import com.meli.socialmeli.dto.response.ProductsOfSellerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meli.socialmeli.dto.PostDto;

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
@GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostFromFollowedDto> getPostsFromFollowedUsers(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsFromFollowedUsers(userId));
    }
    @GetMapping("/promo-post/list")
    public ResponseEntity<ProductsOfSellerDto> listAllProductsInSaleOfSeller(@RequestParam("user_id") Integer userId) {
        return new ResponseEntity<>(postService.listAllProductsInSaleOfSeller(userId), HttpStatus.OK);
    }
}
