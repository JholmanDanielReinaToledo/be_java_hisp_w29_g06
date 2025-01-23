package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    public Optional<User> findById(Integer id);
    public boolean followSeller(User user, Seller seller);

}
