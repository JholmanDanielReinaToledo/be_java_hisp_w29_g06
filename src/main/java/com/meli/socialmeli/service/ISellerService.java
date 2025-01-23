package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.FollowersDto;

public interface ISellerService {

    FollowersDto findFollowersBySeller(Integer sellerId);

}
