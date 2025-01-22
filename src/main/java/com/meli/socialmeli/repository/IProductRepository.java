package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Product;

import java.util.Optional;

public interface IProductRepository {
    Optional<Product> findById(Integer id);
    Optional<Product> add(Product product);
}
