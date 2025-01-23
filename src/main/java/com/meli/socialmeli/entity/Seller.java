package com.meli.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    Integer id;
    String name;
    private List<User> followers = new ArrayList<>();

    public Seller(int i, String vendedor1) {
        this.id = i;
        this.name = vendedor1;
    }

    public void addFollower(User user) {
        followers.add(user); // Método para añadir un seguidor
    }
}
