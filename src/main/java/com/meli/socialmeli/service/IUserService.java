package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.FollowedListResponseDto;
import com.meli.socialmeli.dto.ResponseDto;

public interface IUserService {

    ResponseDto followSeller(Integer userId, Integer sellerId);
    ResponseDto unfollowSeller(Integer userId, Integer sellerId);
    FollowedListResponseDto getFollowedList(Integer userId);
}
