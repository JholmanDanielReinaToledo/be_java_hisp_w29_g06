package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

    private List<Product> products;

}
