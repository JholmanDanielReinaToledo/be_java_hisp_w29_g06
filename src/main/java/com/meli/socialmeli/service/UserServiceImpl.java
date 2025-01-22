package com.meli.socialmeli.service;

import org.springframework.stereotype.Service;

import com.meli.socialmeli.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
}
