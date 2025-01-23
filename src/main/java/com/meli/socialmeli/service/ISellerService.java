package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.FollowersDto;
import com.meli.socialmeli.dto.SellerDto;

public interface ISellerService {

    FollowersDto findFollowersBySeller(Integer sellerId);
    SellerDto countFollowers(Integer id);
}
