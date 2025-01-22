package com.meli.socialmeli.service;

import org.springframework.stereotype.Service;

import com.meli.socialmeli.dto.NumberOfProductsInSaleDto;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.IPostRepository;
import com.meli.socialmeli.repository.IUserRepository;

@Service
public class PostServiceImpl implements IPostService {

    private IPostRepository postRepository;
    private IUserRepository userRepository;

    public PostServiceImpl(IPostRepository postRepository, IUserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public NumberOfProductsInSaleDto getNumberOfProductsInSale(Integer userId) {
        
        if (userRepository.findById(userId).isPresent()){
            String name = userRepository.findById(userId).get().getName();
            Long count = postRepository.getNumberOfProductsInSale(userId);
            return new NumberOfProductsInSaleDto(userId,name,count);
        } else {
            throw new NotFoundException("User not found.");
        }
        
    }
}
