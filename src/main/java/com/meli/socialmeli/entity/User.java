package com.meli.socialmeli.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private List<Seller> follows;
    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.follows = new ArrayList<>();
    }
}
