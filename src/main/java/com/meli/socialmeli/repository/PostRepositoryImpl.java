package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Post> add(Post post) {
        Optional<Post> postExist = this.findById(post.getId());

        if (postExist.isPresent()) {
            return Optional.empty();
        }

        post.setId(posts.size() + 1);
        posts.add(post);

        return Optional.of(post);
    }
}
