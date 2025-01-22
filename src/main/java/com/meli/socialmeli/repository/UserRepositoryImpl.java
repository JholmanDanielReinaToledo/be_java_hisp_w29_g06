package com.meli.socialmeli.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.meli.socialmeli.entity.User;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private List<User> users;

    public UserRepositoryImpl() {
        // Initialize users with sample data
        users = List.of(new User(1, "John Doe",new ArrayList<>()), new User(2, "Jane Smith",new ArrayList<>()));
    }

    public Optional<User> findById(Integer userId) {
        // Implement logic to fetch user from the database based on userId
        Optional<User> user_to_find = users.stream()
                        .filter(user -> user.getId().equals(userId))
                        .findFirst();
        return user_to_find;
    }
}
