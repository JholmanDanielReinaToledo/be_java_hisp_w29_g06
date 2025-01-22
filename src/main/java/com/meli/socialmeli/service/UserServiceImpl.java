package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.ResponseDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDto followSeller(Integer userId, Integer userIdToFollow) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("The user doesn't exists");
        }
        Optional<User> seller = userRepository.findById(userIdToFollow);
        if (seller.isEmpty()) {
            throw new NotFoundException("The seller doesn't exists");
        }
        this.userRepository.followSeller(user.get(), seller.get());
        return new ResponseDto("Vendedor seguido con éxito");
    }
}
