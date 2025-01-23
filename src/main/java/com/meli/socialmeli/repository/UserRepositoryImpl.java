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
    }


    @Override
    public Optional<User> findById(Integer id) {
        return this.users.stream().filter(user -> user.getId().equals(id)).findFirst();
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
