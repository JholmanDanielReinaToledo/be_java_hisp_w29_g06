package com.meli.socialmeli.repository;

import java.util.Optional;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;

public interface IUserRepository {
    Optional<User> findById(Integer id);
    boolean followSeller(User user, Seller seller);
    boolean save(User user);
    boolean unfollowSeller(User user, Seller seller);
}
