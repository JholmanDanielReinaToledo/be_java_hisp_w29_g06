package com.meli.socialmeli.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;


@Repository
public class UserRepositoryImpl implements IUserRepository {

    private List<User> users;

    public UserRepositoryImpl() {
        // Initialize users with sample data
        users = List.of(new User(1, "John Doe",new ArrayList<>()), new User(2, "Jane Smith",new ArrayList<>()));
    }


    @Override
    public Optional<User> findById(Integer id) {
        return this.users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public boolean followSeller(User user, User seller) {
        if (seller instanceof Seller) {
            return user.getFollows().add((Seller) seller);
        }
        return false;
    }
}
