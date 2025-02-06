package com.meli.socialmeli.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.meli.socialmeli.constants.Messages;
import com.meli.socialmeli.dto.response.ResponseDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.ConflictException;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.ISellerRepository;
import com.meli.socialmeli.repository.IUserRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private ISellerRepository sellerRepository;
    
    @InjectMocks
    private UserServiceImpl userService;

    //Test variables
    private User userWithNoFollows;
    private User userWithFollows;
    private Seller sellerWithNoFollowers;
    private Seller sellerWithFollowers;

    @BeforeEach
    public void setUp() {
        userWithNoFollows = User.builder()
                        .id(1)
                        .name("Usuario de Prueba")
                        .follows(Arrays.asList())
                        .build();
        sellerWithNoFollowers = Seller.builder()
                                             .id(1)
                                             .name("Vendedor de Prueba")
                                             .followers(Arrays.asList()).build();
        userWithFollows = User.builder()
                              .id(2)
                              .name("Usuario de Prueba con Seguidos")
                              .follows(Arrays.asList())
                              .build();
        sellerWithFollowers = Seller.builder()
                                    .id(2)
                                    .name("Vendedor de Prueba con Seguidores")
                                    .followers(Arrays.asList())
                                    .build();
        userWithFollows.setFollows(Arrays.asList(sellerWithFollowers));
        sellerWithFollowers.setFollowers(Arrays.asList(userWithFollows));
    }
    @Test
    @DisplayName("T-0001: User follows a Seller ")
    public void followSellerOk() {
        // Arrange
        // Declaramos los Id que se le pasan a la función de service
        Integer userId = 1;
        Integer sellerId = 1;
        // Mockeamos que el usuario y el vendedor existan con esos ids en el repositorio
        when(userRepository.getById(userId)).thenReturn(Optional.of(userWithNoFollows));
        when(sellerRepository.getById(sellerId)).thenReturn(Optional.of(sellerWithNoFollowers));
        // Act
        // Ejecutamos la función de service para seguir al vendedor
        var result = userService.followSeller(userId, sellerId);
        // Assert
        // Verificamos que se devuelva el DTO con el mensaje de success
        Assertions.assertEquals(ResponseDto.builder()
                                           .message(Messages.SUCCESS_FOLLOW)
                                           .build(), result);
    }

    @Test
    @DisplayName("T-0001: User or Seller not found")
    public void followSellerNotFoundException() {
        //Arrange

        // Id de usuarios que existen o no existen
        Integer userIdValid = 1;
        Integer sellerIdInvalid = 0;
        Integer userIdInvalid = 0;
        Integer sellerIdValid = 1;

        // Mockeamos que existen o no existen segun el que reciba el repository
        when(userRepository.getById(sellerIdValid)).thenReturn(Optional.of(userWithNoFollows));
        when(userRepository.getById(sellerIdInvalid)).thenReturn(Optional.empty());
        when(sellerRepository.getById(sellerIdInvalid)).thenReturn(Optional.empty());
        
        // Act & Assert
        // Verificamos que se lancen las excepciones cuando alguno de los dos usuarios no exista
        Assertions.assertThrows(NotFoundException.class,
                                () -> userService.followSeller(userIdValid, sellerIdInvalid));
        Assertions.assertThrows(NotFoundException.class,
                                () -> userService.followSeller(userIdInvalid, sellerIdValid));
    }

    @Test
    @DisplayName("T-0001: User already follows a Seller")
    public void followSellerConflictException() {
        // Arrange
        Integer userId = 2;
        Integer sellerId = 2;
        // Mockeamos que el usuario ya sigue al vendedor
        when(userRepository.getById(userId)).thenReturn(Optional.of(userWithFollows));
        when(sellerRepository.getById(sellerId)).thenReturn(Optional.of(sellerWithFollowers));
        when(userRepository.isFollower(userWithFollows, sellerWithFollowers)).thenReturn(true);
        // Act & Assert
        Assertions.assertThrows(ConflictException.class,
                                () -> userService.followSeller(userId, sellerId));
    }

}
