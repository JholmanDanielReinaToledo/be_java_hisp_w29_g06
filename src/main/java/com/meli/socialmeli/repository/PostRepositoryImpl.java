package com.meli.socialmeli.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.entity.Seller;

@Repository
public class PostRepositoryImpl implements IPostRepository {
    private List<Post> posts;

    public PostRepositoryImpl() {
        this.posts = new ArrayList<Post>();
        // Initialize posts with sample data
        Seller sample_seller = new Seller(1, "seller1");
        Seller sample_seller2 = new Seller(2,"seller2");
        // Add more posts as needed
        this.posts.add(new Post(1,
                                LocalDate.now(),
                                20.0,
                                new Product(),
                                sample_seller,
                                0.20,
                                true,
                                2));
        this.posts.add(new Post(2,
                                LocalDate.now(),
                                30.0,
                                new Product(),
                                sample_seller2,
                                0.15,
                                false,
                                1));
        this.posts.add(new Post(3,
                                LocalDate.now(),
                                40.0,
                                new Product(),
                                sample_seller,
                                0.10,
                                true,
                                3));
        this.posts.add(new Post(4,
                                LocalDate.now(),
                                50.0,
                                new Product(),
                                sample_seller2,
                                0.05,
                                false,
                                4));

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
