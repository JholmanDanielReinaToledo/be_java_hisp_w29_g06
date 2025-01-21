package com.meli.socialmeli.service;

import com.meli.socialmeli.repository.IPostRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    private IPostRepository postRepository;

    public ProductServiceImpl(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
