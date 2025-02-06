package com.meli.socialmeli.service;

import com.meli.socialmeli.constants.Messages;
import com.meli.socialmeli.dto.FollowedDto;
import com.meli.socialmeli.dto.FollowedListResponseDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundOrderException;
import com.meli.socialmeli.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.junit.jupiter.MockitoExtension;
import com.meli.socialmeli.dto.response.ResponseDto;
import com.meli.socialmeli.exception.ConflictException;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.ISellerRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private ISellerRepository sellerRepository;

    @InjectMocks
    private UserServiceImpl userService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear vendedores
        Seller seller1 = new Seller(1, "Carlos", List.of());
        Seller seller2 = new Seller(2, "Ana", List.of());
        Seller seller3 = new Seller(3, "Beatriz", List.of());

        // Crear un usuario de prueba
        User user = new User();
        user.setId(1);
        user.setName("UsuarioTest");
        user.setFollows(Arrays.asList(seller1, seller2, seller3));

        // Simular el comportamiento del repositorio para devolver el usuario
        when(userRepository.getById(1)).thenReturn(Optional.of(user));

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
    //Test variables
    private User userWithNoFollows;
    private User userWithFollows;
    private Seller sellerWithNoFollowers;
    private Seller sellerWithFollowers;

    private final Integer userId = 1;
    private final Integer sellerId = 1;


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
        when(userRepository.getById(userIdValid)).thenReturn(Optional.of(userWithNoFollows));
        when(userRepository.getById(userIdInvalid)).thenReturn(Optional.empty());
        when(sellerRepository.getById(sellerIdInvalid)).thenReturn(Optional.empty());

        // Act & Assert
        // Verificamos que se lancen las excepciones cuando alguno de los dos usuarios no exista
        // Situaciones que lanzan excepcion:
        // - que user no exista y seller si exista -> como la verificación es secuencial, incluye ambos casos de si el seller existe o no existe
        // - que user si exista y seller no exista
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

    @Test
    void testOrderNull() {
        assertThrows(NotFoundOrderException.class, () ->
                userService.getFollowedList(1, null), Messages.ORDER_NOT_FOUND);
    }

    @Test
    void testOrderDescendente() {
        FollowedListResponseDto response = userService.getFollowedList(1, "name_desc");
        List<FollowedDto> followed = response.getFollowed();

        assertEquals("Carlos", followed.get(0).getUser_name());
        assertEquals("Beatriz", followed.get(1).getUser_name());
        assertEquals("Ana", followed.get(2).getUser_name());
    }

    @Test
    void testOrderAscendente() {
        FollowedListResponseDto response = userService.getFollowedList(1, "name_asc" );
        List<FollowedDto> followed = response.getFollowed();

        assertEquals("Ana", followed.get(0).getUser_name());
        assertEquals("Beatriz", followed.get(1).getUser_name());
        assertEquals("Carlos", followed.get(2).getUser_name());
    }

    @Test
    void testOrderInvalido() {
        assertThrows(NotFoundOrderException.class, () ->
                userService.getFollowedList(1, "El orden no es válido"), Messages.ORDER_NOT_FOUND);
    }
}


