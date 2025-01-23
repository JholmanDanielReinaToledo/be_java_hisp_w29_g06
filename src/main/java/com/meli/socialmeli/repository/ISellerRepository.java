package com.meli.socialmeli.repository;

import java.util.Optional;

import com.meli.socialmeli.entity.Seller;

public interface ISellerRepository {
    Optional<Seller> findById(Integer id);
}
