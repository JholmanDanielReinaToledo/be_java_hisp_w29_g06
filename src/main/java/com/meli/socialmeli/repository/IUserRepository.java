package com.meli.socialmeli.repository;
import com.meli.socialmeli.entity.User;

public interface IUserRepository {
    public User findById(Integer id);
}
