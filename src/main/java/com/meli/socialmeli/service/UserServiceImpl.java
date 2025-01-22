package com.meli.socialmeli.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dto.FollowersDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public FollowersDto findFollowersBySeller(Integer sellerId){
        List<User> listUser = userRepository.searchFollowersBySeller(sellerId);
        if(listUser.isEmpty()){
            throw new NotFoundException("No se encontraron Folowers asociados al vendendor.");
        }

        String sellerName = userRepository.searchSellerById(sellerId);

        return new FollowersDto(sellerId,sellerName,listUser);
    }
}
