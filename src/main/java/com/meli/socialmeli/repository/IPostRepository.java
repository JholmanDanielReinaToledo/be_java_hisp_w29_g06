package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Post;

import java.util.Optional;

public interface IPostRepository {
    Optional<Post> add(Post post);
    Long getNumberOfProductsInSale(Integer user_id);
    Optional<Post> findById(Integer id);
}
