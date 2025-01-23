package com.meli.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private List<Seller> follows = new ArrayList<>();

    public User(int i, String usuario1) {
        this.id = i;
        this.name = usuario1;
    }

    public void follow(Seller seller) {
        follows.add(seller); // Método para seguir un vendedor
    }
}
