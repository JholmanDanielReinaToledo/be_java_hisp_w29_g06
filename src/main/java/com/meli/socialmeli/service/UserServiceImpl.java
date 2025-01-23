package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.FollowedDto;
import com.meli.socialmeli.dto.ResponseDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.ISellerRepository;
import com.meli.socialmeli.repository.IUserRepository;
import com.meli.socialmeli.repository.SellerRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;
    private ISellerRepository sellerRepository;

    public UserServiceImpl(IUserRepository userRepository, ISellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
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
        this.sellerRepository.addFollower(seller.get(), user.get());
        return new ResponseDto("Vendedor seguido con éxito");
    }

}
