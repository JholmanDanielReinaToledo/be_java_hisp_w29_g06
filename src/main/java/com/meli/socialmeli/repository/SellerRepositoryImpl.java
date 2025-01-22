package com.meli.socialmeli.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class SellerRepositoryImpl implements ISellerRepository {

    private List<Seller> sellers = new ArrayList<>();

    @Override
    public Optional<Seller> findById(Integer id) {
        return this.sellers.stream().filter(seller -> seller.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> searchFollowersBySeller(Integer sellerId) {
        return sellers.stream().filter(seller -> seller.getId().equals(sellerId)).findFirst().get().getFollowers();
    }

    @Override
    public String searchSellerById(Integer sellerId) {
        return sellers.stream().filter(seller -> seller.getId().equals(sellerId)).findFirst().get().getName();

    }

}
