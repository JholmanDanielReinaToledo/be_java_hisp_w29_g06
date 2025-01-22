package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private List<User> users;
    private List<Seller> sellers;


    @Override
    public List<User> searchFollowersBySeller(Integer sellerId) {
        Optional<User> seller= users.stream().filter(user -> user.getId().equals(sellerId)).findFirst();
        if (seller.isPresent() && seller.get() instanceof Seller seller1) {
            return seller1.getFollowers();
        }
        return List.of();
    }

    @Override
    public String searchSellerById(Integer sellerId) {
        Optional<User> seller= users.stream().filter(user -> user.getId().equals(sellerId)).findFirst();
        if (seller.isPresent() && seller.get() instanceof Seller seller1) {
            return seller1.getName();
        }
        return "Not found";

    }
}
