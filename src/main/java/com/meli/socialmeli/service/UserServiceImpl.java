package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.FollowedDto;
import com.meli.socialmeli.dto.FollowedListResponseDto;
import com.meli.socialmeli.dto.ResponseDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.ConflictException;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.ISellerRepository;
import com.meli.socialmeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
        if (userId.equals(sellerId)) {
            throw new ConflictException("No puedes seguirte a ti mismo");
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("El usuario con ese Id no existe");
        }
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        if (seller.isEmpty()) {
            throw new NotFoundException("El vendedor con ese Id no existe");
        }
        if (this.userRepository.userFollowsSeller(user.get(), seller.get())) {
            throw new ConflictException("Ya sigues a este vendedor");
        }
        this.userRepository.followSeller(user.get(), seller.get());
        this.sellerRepository.addFollower(seller.get(), user.get());
        return new ResponseDto("Vendedor seguido con éxito");
    }

    @Override
    public FollowedListResponseDto getFollowedList(Integer userId, String order) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("El usuario con ese Id no existe");
        }
        List<Seller> sellers = user.get().getFollows();

        if ("name_desc".equals(order)) {
            sellers.sort(Comparator.comparing(Seller::getName).reversed());
        } else {
            sellers.sort(Comparator.comparing(Seller::getName));
        }
        List<FollowedDto> followedDtos = sellers.stream().map(seller -> new FollowedDto(seller.getId(),seller.getName())).toList();
        return new FollowedListResponseDto(user.get().getId(),user.get().getName(),followedDtos);
    }

}
