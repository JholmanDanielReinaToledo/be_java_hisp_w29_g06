package com.meli.socialmeli.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends User {
    private List<User> followers;
    public Seller(Integer id, String name) {
        super(id, name, new ArrayList<>());
        this.followers = new ArrayList<>();
    }
}
