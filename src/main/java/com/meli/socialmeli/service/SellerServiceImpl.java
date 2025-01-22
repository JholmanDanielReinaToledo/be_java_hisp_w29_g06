package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.FollowersDto;
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
        List<User> listUser = sellerRepository.searchFollowersBySeller(sellerId);
        if(listUser.isEmpty()){
            throw new NotFoundException("No se encontraron Folowers asociados al vendendor.");
        }

        String sellerName = sellerRepository.searchSellerById(sellerId);

        return new FollowersDto(sellerId,sellerName,listUser);
    }


}
