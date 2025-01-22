package com.meli.socialmeli.controller;


import com.meli.socialmeli.dto.PostDto;
import com.meli.socialmeli.dto.response.ResponseWrapperDto;
import com.meli.socialmeli.service.IPostService;
import com.meli.socialmeli.service.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
