package com.meli.socialmeli.repository;

import java.util.List;
import java.util.Optional;

import com.meli.socialmeli.entity.Seller;
import org.springframework.stereotype.Repository;

@Repository
public class SellerRepositoryImpl implements ISellerRepository{
    
    private List<Seller> sellers;
    @Override
    public Optional<Seller> findById(Integer id) {
        return this.sellers.stream().filter(seller -> seller.getId().equals(id)).findFirst();
    }
}
