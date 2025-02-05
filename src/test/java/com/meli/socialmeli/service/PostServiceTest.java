package com.meli.socialmeli.service;


import com.meli.socialmeli.constants.OrderType;
import com.meli.socialmeli.constants.PostOrder;
import com.meli.socialmeli.dto.response.PostFromFollowedDto;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundOrderException;
import com.meli.socialmeli.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    PostRepositoryImpl postRepository;
    @Mock
    UserRepositoryImpl userRepository;

    @InjectMocks
    PostServiceImpl service;

    private User user1;
    private Seller seller1;
    private Product product1;
    private Post post1;

    @BeforeEach
    void setUp() {
        seller1 = createTestSeller();
        user1 = createTestUser(seller1);
        product1 = createTestProduct();
        post1 = createTestPost(product1, seller1);
    }

    @Test
    @DisplayName("Should throw NotFoundOrderException when order is not valid")
    void getPostsFromFollowedUsers_OrderNotFoundException() {
        //Arrange
        Integer userId = 1;
        Integer order = 0;
        when(userRepository.getById(userId)).thenReturn(Optional.of(user1));

        //Act & Assert
        assertThrows(NotFoundOrderException.class,
                () -> service.getPostsFromFollowedUsers(userId, order));
    }

    @Test
    @DisplayName("Should return a list of posts from followed users")
    void getPostsFromFollowedUsers_Ok() {
        //Arrange
        Integer userId = 1;
        Integer order = PostOrder.ASCENDING.getValue();
        when(userRepository.getById(userId)).thenReturn(Optional.of(user1));
        when(postRepository.getPostsBySellers(user1.getFollows())).thenReturn(List.of(post1));

        //Act
        PostFromFollowedDto response = service.getPostsFromFollowedUsers(userId, order);

        //Assert
        verify(userRepository).getById(userId);
        verify(postRepository).getPostsBySellers(user1.getFollows());
        assert(response.getPosts().size() > 0); //Al menos 1 post
    }

    private User createTestUser(Seller seller) {
        User user = new User(1, "Juan", new ArrayList<>());
        user.getFollows().add(seller);
        return user;
    }

    private Seller createTestSeller() {
        return new Seller(1, "Juansito", new ArrayList<>());
    }

    private Product createTestProduct() {
        return Product.builder()
                .id(1)
                .name("Juego")
                .type("Gamer")
                .brand("Box")
                .color("Black")
                .notes("Notes this is a game for box")
                .build();
    }

    private Post createTestPost(Product product, Seller seller) {
        return Post.builder()
                .id(1)
                .date(LocalDate.now())
                .price(1000.0)
                .product(product)
                .seller(seller)
                .discount(0.1)
                .hasPromo(true)
                .category(100)
                .build();
    }

}
