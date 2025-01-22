package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Post;

import java.util.Optional;

public interface IPostRepository {
    Optional<Post> findById(Integer id);
    Optional<Post> add(Post post);
}
