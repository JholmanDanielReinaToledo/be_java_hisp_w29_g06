package com.meli.socialmeli.service;

import com.meli.socialmeli.repository.IPostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService {

    private IPostRepository postRepository;

    public PostServiceImpl(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
