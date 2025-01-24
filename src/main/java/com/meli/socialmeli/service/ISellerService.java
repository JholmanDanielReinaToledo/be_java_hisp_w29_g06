package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.FollowersDto;
import com.meli.socialmeli.dto.SellerDto;

public interface ISellerService {
    FollowersDto getFollowersBySellerId(Integer sellerId, String order);
    SellerDto countFollowers(Integer id);
}
