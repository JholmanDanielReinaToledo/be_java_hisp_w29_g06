package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.SellerDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.ISellerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerServiceImpl implements ISellerService{

    ISellerRepository sellerRepository;

    public SellerServiceImpl(ISellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public SellerDto countFollowers(Integer id) {
        Optional<Seller> optionalSeller = sellerRepository.findById(id);
        if (optionalSeller.isPresent()) {
            Seller seller = optionalSeller.get();
            Integer followersCount = seller.getFollowers().size();
            return new SellerDto(seller.getId(), seller.getName(), followersCount);
        }
        throw new NotFoundException("El vendedor con el id " + id + " no existe.");
    }
}
