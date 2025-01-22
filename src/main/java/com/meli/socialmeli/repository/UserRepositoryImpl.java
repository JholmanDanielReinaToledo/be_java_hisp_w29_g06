package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private List<User> users;

    public UserRepositoryImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return this.users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Seller> findSellerById(Integer id) {
        Optional<User> user = this.users.stream().filter(u -> u.getId().equals(id)).findFirst();
        if (user.isPresent() && user.get() instanceof Seller) {
            return Optional.of((Seller) user.get());
        }
        return Optional.empty();
    }
}
