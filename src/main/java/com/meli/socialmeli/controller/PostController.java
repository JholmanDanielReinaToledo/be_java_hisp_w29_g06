package com.meli.socialmeli.controller;


import com.meli.socialmeli.service.IPostService;
import com.meli.socialmeli.service.PostServiceImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private IPostService postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }
}
