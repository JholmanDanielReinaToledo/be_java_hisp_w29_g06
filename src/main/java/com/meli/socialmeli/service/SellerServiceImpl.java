package com.meli.socialmeli.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dto.FollowersDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.ISellerRepository;

import java.util.List;

public class SellerServiceImpl implements ISellerService{

    private ISellerRepository sellerRepository;

    public SellerServiceImpl(ISellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public FollowersDto findFollowersBySeller(Integer sellerId){
        ObjectMapper objectMapper = new ObjectMapper();

        Seller seller = sellerRepository.findById(sellerId).orElse(null);
        List<User> listUser = seller.getFollowers();

        if(listUser.isEmpty()){
            throw new NotFoundException("No se encontraron Folowers asociados al vendendor.");
        }

        return objectMapper.convertValue(seller, FollowersDto.class);
    }


}
