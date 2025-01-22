package com.meli.socialmeli.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private List<Seller> follows = new ArrayList<>();


    public void follow(Seller seller) {
        follows.add(seller);
    }


}
