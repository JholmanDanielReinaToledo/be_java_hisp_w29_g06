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
        this.users.add(new Seller(123, "Pepito", List.of(), List.of()));
    }

    @Override
    public Optional<User> findById(Integer id) {
        return this.users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}
