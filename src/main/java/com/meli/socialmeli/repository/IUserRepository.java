package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.User;

import java.util.List;

public interface IUserRepository {
    List<User> searchFollowersBySeller(Integer sellerId);
    String searchSellerById(Integer sellerId);

}
