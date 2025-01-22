package com.meli.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends User {

    private List<User> followers;

    public Seller(Integer id, String name, List<Seller> follows, List<User> followers) {
        super(id, name, follows);
        this.followers = followers;
    }
}
