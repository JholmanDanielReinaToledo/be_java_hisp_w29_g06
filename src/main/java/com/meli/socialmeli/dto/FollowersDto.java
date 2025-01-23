package com.meli.socialmeli.dto;

import com.meli.socialmeli.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowersDto {
    private Integer user_id;
    private String user_name;
    private List<User> followers;
}
