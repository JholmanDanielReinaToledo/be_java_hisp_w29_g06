package com.meli.socialmeli.repository;
import java.util.Optional;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;

import java.util.List;

public interface IUserRepository {

    public Optional<User> findById(Integer id);
    public boolean followSeller(User user, Seller seller);
}
