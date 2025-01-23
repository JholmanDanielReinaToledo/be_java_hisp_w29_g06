package com.meli.socialmeli.repository;

import java.util.List;
import java.util.Optional;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;

public interface ISellerRepository {
    public Optional<Seller> findById(Integer id);

}
