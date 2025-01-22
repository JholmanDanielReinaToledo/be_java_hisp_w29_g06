package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.ResponseDto;

public interface IUserService {

    ResponseDto followSeller(Integer userId, Integer userIdToFollow);
}
