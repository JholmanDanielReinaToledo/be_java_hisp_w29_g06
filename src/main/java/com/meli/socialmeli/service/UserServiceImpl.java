package com.meli.socialmeli.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.meli.socialmeli.dto.ResponseDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.ISellerRepository;
import com.meli.socialmeli.repository.IUserRepository;
@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;
    private ISellerRepository sellerRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public ResponseDto followSeller(Integer userId, Integer sellerId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("El usuario con ese Id no existe");
        }
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        if (seller.isEmpty()) {
            throw new NotFoundException("El vendedor con ese Id no existe");
        }
        this.userRepository.followSeller(user.get(), seller.get());
        return new ResponseDto("Vendedor seguido con éxito");
    }
}
