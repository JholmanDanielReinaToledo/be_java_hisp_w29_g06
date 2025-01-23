package com.meli.socialmeli.repository;

import java.util.Optional;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;

public interface ISellerRepository {
    public Optional<Seller> findById(Integer id);
    public boolean addFollower(Seller seller, User user);
    boolean isFollower(Seller seller, User user);
    boolean removeFollower(Seller seller, User user);
}
