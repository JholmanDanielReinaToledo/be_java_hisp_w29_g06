package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.User;

import java.util.Optional;

public interface IUserRepository {

    Integer count();
    Optional<User> findById(Integer id);

}
