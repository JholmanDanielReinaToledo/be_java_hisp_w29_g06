package com.meli.socialmeli.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class SellerRepositoryImpl implements ISellerRepository{
    
    private List<Seller> sellers;

    public SellerRepositoryImpl() {
        sellers = new ArrayList<>();
        sellers.add(new Seller(1, "Vendedor1", new ArrayList<>()));
        sellers.add(new Seller(2, "Vendedor2", new ArrayList<>()));
    }
    @Override
    public Optional<Seller> findById(Integer id) {
        return this.sellers.stream().filter(seller -> seller.getId().equals(id)).findFirst();
    }


}
