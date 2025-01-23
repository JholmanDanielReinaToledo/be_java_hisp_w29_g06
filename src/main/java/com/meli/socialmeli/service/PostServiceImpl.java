package com.meli.socialmeli.service;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.repository.ISellerRepository;
import org.springframework.stereotype.Service;

import com.meli.socialmeli.dto.NumberOfProductsInSaleDto;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.IPostRepository;
import com.meli.socialmeli.repository.IUserRepository;

import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {

    private IPostRepository postRepository;

    private IUserRepository userRepository;

    private ISellerRepository sellerRepository;

    public PostServiceImpl(
            IPostRepository postRepository,
            IUserRepository userRepository,
            ISellerRepository sellerRepository
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
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

    @Override
    public void listAllProductsInSaleOfSeller(Integer userId) {
        Optional<Seller> seller = sellerRepository.findById(userId);
        if (seller.isPresent()){
            String name = seller.get().getName();

        }
    }
}
