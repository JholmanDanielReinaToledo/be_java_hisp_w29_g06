package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private List<User> users;

    public UserRepositoryImpl() {
        this.users = new ArrayList<>();
        this.users.add(
                new User(123, "Juan", new ArrayList<>())
        );
        users.add(new User(1, "aJohn Doe",new ArrayList<>()));
        users.add(new User(2, "bJane Smith",new ArrayList<>()));
        users.add(new User(3, "cJhonson",new ArrayList<>()));
        users.add(new User(4, "dJulian",new ArrayList<>()));
    }


    @Override
    public Optional<User> findById(Integer id) {
        return this.users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public boolean isFollower(User user, Seller seller) {
        return user.getFollows().contains(seller);
    }

    @Override
    public boolean followSeller(User user, Seller seller) {
        return user.getFollows().add(seller);
    }

    @Override
    public boolean unfollowSeller(User user, Seller seller) {
        return user.getFollows().remove(seller);
    }

    @Override
    public boolean save(User user) {
        return this.users.add(user);
    }

}
