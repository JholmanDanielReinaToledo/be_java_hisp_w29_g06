package com.meli.socialmeli.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dto.FollowersDto;
import com.meli.socialmeli.dto.SellerDto;
import com.meli.socialmeli.dto.UserDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.ISellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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

        List<UserDto> listUserDto = listUser.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setUser_id(user.getId());
                    userDto.setUser_name(user.getName());
                    return userDto;
                })
                .collect(Collectors.toList());


        return new FollowersDto(seller.getId(),seller.getName(),listUserDto);
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
