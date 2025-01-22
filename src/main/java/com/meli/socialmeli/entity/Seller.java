package com.meli.socialmeli.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Seller extends User {
    private List<User> followers = new ArrayList<>();
    public Seller(Integer id, String name) {
        super(id, name, new ArrayList<>());
    }
    public void addFollower(User user) {
        followers.add(user);
    }

}
