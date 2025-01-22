package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private List<User> users;

    public UserRepositoryImpl() {
        this.users = new ArrayList<>();
        this.users.add(new User(1, "Pepito", List.of()));
        this.users.add(new User(2, "Pepito2", List.of()));
        this.users.add(new User(3, "Pepito3", List.of()));
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
