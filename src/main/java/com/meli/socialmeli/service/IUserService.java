package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.FollowedDto;
import com.meli.socialmeli.dto.ResponseDto;

import java.util.List;

public interface IUserService {

    ResponseDto followSeller(Integer userId, Integer sellerId);
}
