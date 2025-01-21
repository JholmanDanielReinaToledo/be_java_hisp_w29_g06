package com.meli.socialmeli.service;

import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService {

    private IPostService postService;

    public PostServiceImpl(IPostService postService) {
        this.postService = postService;
    }
}
