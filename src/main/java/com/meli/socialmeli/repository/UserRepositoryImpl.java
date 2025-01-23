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
        users.add(new User(1, "John Doe",new ArrayList<>()));
        users.add(new User(2, "Jane Smith",new ArrayList<>()));
        users.add(new User(3, "Jhonson",new ArrayList<>()));
    }


    @Override
    public Optional<User> findById(Integer id) {
        return this.users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public boolean userFollowsSeller(User user, Seller seller) {
        return user.getFollows().contains(seller);
    }

    @Override
    public boolean followSeller(User user, Seller seller) {
        return user.getFollows().add(seller);
    }
}
