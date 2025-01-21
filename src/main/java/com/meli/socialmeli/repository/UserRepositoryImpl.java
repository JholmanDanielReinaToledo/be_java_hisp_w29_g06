package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private List<User> users;

}
