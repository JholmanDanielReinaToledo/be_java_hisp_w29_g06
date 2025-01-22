package com.meli.socialmeli.repository;
import java.util.Optional;

import com.meli.socialmeli.entity.User;
public interface IUserRepository {
    public Optional<User> findById(Integer id);
}
