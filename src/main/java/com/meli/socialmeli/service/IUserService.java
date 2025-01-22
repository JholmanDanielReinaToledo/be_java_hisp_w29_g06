package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.FollowersDto;
import com.meli.socialmeli.exception.NotFoundException;

import java.util.List;

public interface IUserService {

    FollowersDto findFollowersBySeller(Integer sellerId);
}
