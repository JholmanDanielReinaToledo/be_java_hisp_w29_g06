package com.meli.socialmeli.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundException;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private List<User> users;

    public UserRepositoryImpl() {
        // Initialize users with sample data
        users = List.of(new User(1, "John Doe",new ArrayList<>()), new User(2, "Jane Smith",new ArrayList<>()));
    }

    public User findById(Integer userId) {
        // Implement logic to fetch user from the database based on userId
        User user_to_find = users.stream()
                        .filter(user -> user.getId().equals(userId))
                        .findFirst()
                        .orElse(null);
        if (user_to_find != null) {
            return user_to_find;
        } else {
            throw new NotFoundException("No existe user with id " + userId);
        }
    }
}
