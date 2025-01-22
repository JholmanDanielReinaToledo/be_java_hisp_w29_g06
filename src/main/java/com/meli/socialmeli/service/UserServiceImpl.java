package com.meli.socialmeli.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dto.SellerDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.IUserRepository;
import com.meli.socialmeli.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public SellerDto countFollowers(Integer id) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("No existe el usuario con id: " + id));
        if (user instanceof Seller seller) {
            Integer followers = seller.getFollowers().size();
            SellerDto sellerDto = mapper.convertValue(seller, SellerDto.class);
            sellerDto.setFollowers(followers);
            return sellerDto;
        }
        throw new NotFoundException("El usuario con id " + id + " no es un vendedor.");
    }
}
