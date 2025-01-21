package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryImpl implements IPostRepository {
    private List<Post> posts;
}
