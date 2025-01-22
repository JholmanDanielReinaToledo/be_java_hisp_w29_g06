package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;

import java.util.Optional;

public interface IUserRepository {

    Optional<User> findById(Integer id);
    boolean followSeller(User user, User seller);

}
