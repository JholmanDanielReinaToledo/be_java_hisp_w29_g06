package com.meli.socialmeli.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.meli.socialmeli.entity.Post;

@Repository
public class PostRepositoryImpl implements IPostRepository {
    private List<Post> posts;

    public PostRepositoryImpl() {
        this.posts = new ArrayList<Post>();
        

    }
    
    public Long getNumberOfProductsInSale(Integer user_id) {
        return posts.stream()
                    .filter(post -> (post.getSeller()
                                        .getId()
                                        .equals(user_id)
                                    && 
                                    post.getHasPromo()))
                    .count();
    }
}
