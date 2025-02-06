package com.meli.socialmeli.service;

import com.meli.socialmeli.constants.Messages;
import com.meli.socialmeli.dto.NumberOfProductsInSaleDto;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.repository.PostRepositoryImpl;
import com.meli.socialmeli.repository.SellerRepositoryImpl;
import com.meli.socialmeli.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationT7T11 {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SellerRepositoryImpl sellerRepository;

    @Autowired
    private PostRepositoryImpl postRepository;

    @Mock
    private PostServiceImpl postService;

    @Autowired
    private MockMvc mockMvc;

    private User user;
    private Seller seller;

    @BeforeEach
    public void beforeEach() {
        user = new User(20, "Pepito", new ArrayList<>());
        seller = new Seller(20, "Juanito", new ArrayList<>());
        this.userRepository.save(user);
        this.sellerRepository.save(seller);
        sellerRepository.addFollower(seller, user);
    }

    @Test
    public void testUnfollowSellerSuccess() throws Exception {
        mockMvc.perform(post("/users/20/unfollow/20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(Messages.SUCCESS_UNFOLLOW));

        mockMvc.perform(post("/users/20/unfollow/20"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(Messages.USER_NOT_FOLLOWING_SELLER));
    }





}