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
        this.users = new ArrayList<>();
        this.users.add(
                new User(123, "Juan", new ArrayList<>())
        );
        users.add(new User(1, "John Doe",new ArrayList<>()));
        users.add(new User(2, "Jane Smith",new ArrayList<>()));
        users.add(new User(3, "Jhonson",new ArrayList<>()));
    }


    @Override
    public Optional<User> findById(Integer id) {
        return this.users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public boolean followSeller(User user, Seller seller) {
        return user.getFollows().add(seller);
    }
    
    @Override
    public boolean unfollowSeller(User user, Seller seller) {
        return user.getFollows().remove(seller);
    }
}
