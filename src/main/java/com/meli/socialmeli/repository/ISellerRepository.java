package com.meli.socialmeli.repository;

import java.util.Optional;

import com.meli.socialmeli.entity.Seller;

public interface ISellerRepository {
    public Optional<Seller> findById(Integer id);
}
