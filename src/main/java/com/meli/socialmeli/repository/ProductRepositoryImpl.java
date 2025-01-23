package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

    private List<Product> products;

    public ProductRepositoryImpl() {
        products = new ArrayList<>();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Product> add(Product product) {
        Optional<Product> existingProduct = findById(product.getId());

        if (existingProduct.isPresent()) {
            return Optional.empty();
        }

        products.add(product);
        return Optional.of(product);
    }
}
