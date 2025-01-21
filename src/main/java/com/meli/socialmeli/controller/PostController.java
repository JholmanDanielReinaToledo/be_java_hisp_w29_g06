package com.meli.socialmeli.controller;


import com.meli.socialmeli.service.IPostService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }
}
