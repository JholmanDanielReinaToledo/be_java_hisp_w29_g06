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
        users = new ArrayList<>();
    }


    @Override
    public Integer count() {
        return 0;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}
