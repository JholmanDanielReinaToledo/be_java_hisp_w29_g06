package com.meli.socialmeli.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meli.socialmeli.dto.NumberOfProductsInSaleDto;
import com.meli.socialmeli.dto.PostDto;
import com.meli.socialmeli.dto.response.PostFromFollowedDto;
import com.meli.socialmeli.dto.response.ProductsOfSellerDto;
import com.meli.socialmeli.dto.response.ResponseDto;
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
    public ResponseEntity<ResponseDto> addPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.addPost(postDto), HttpStatus.CREATED);
    }

    @PostMapping("/promo-post")
    public ResponseEntity<ResponseDto> createPromoPost(@RequestBody PostDto promoPostDto) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.addPromoPost(promoPostDto));
    }

    @GetMapping("/promo-post/count")
    public ResponseEntity<NumberOfProductsInSaleDto> getNumberOfProductsInSaleOfSeller(@RequestParam(name="user_id") Integer sellerId) {
        return new ResponseEntity<>(postService.getNumberOfProductsInSaleBySellerId(sellerId), HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostFromFollowedDto> getPostsFromFollowedUsers(
            @PathVariable Integer userId,
            @RequestParam(required = false) String order
    ) {
        if (order == null){
            return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsFromFollowedUsers(userId,0));
        } else if(order.equals("date_asc")) {
            return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsFromFollowedUsers(userId,1));
        } else if(order.equals("date_desc")) {
            return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsFromFollowedUsers(userId,2));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
        }
    }

    @GetMapping("/promo-post/list")
    public ResponseEntity<ProductsOfSellerDto> listAllProductsInSaleOfSeller(@RequestParam("user_id") Integer sellerId) {
        return new ResponseEntity<>(postService.getAllProductsInSaleBySellerId(sellerId), HttpStatus.OK);
    }
}
