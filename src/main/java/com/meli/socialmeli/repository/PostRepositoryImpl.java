package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.entity.Seller;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public class PostRepositoryImpl implements IPostRepository {
    private List<Post> posts;

    public PostRepositoryImpl() {
        posts = new ArrayList<>();
        posts.addAll(List.of(
                new Post(
                        1,
                        LocalDate.now(),
                        12000.0,
                        new Product(1, "Consola", "Gaming", "Asus", "Negra", "Consola negra para venta"),
                        new Seller(234, "Pepito", List.of()),
                        0.25,
                        true,
                        100
                )
        ));
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return posts.stream().filter(post1 -> post1.getId() == id).findFirst();
    }

    @Override
    public Optional<Post> add(Post post) {
        Optional<Post> postExist = this.findById(post.getId());

        if (postExist.isPresent()) {
            return Optional.empty();
        }

        post.setId(posts.size() + 1);
        posts.add(post);

        return Optional.of(post);
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

    @Override
    public List<Post> findPostsInSaleByUserId(Integer userId) {
        return this.posts.stream().filter(
                p -> p.getSeller().getId().equals(userId) && p.getHasPromo()
        ).toList();
    }
}
