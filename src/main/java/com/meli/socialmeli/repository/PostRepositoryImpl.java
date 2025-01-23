package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Seller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class PostRepositoryImpl implements IPostRepository {
    private List<Post> posts;

    public PostRepositoryImpl() {
        posts = new ArrayList<>();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return posts.stream().filter(post1 -> post1.getId() == id).findFirst();
    }

    @Override
    public List<Post> getPostsBySellers(List<Seller> sellers) {
        return sellers.stream()
                .flatMap(seller -> posts.stream()
                        .filter(post -> post.getSeller().getId().equals(seller.getId()))
                        .toList().stream())
                .collect(Collectors.toList());
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
}
