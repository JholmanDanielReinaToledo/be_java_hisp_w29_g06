package com.meli.socialmeli.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.meli.socialmeli.dto.FollowedDto;
import com.meli.socialmeli.dto.FollowedListResponseDto;
import com.meli.socialmeli.exception.NotFoundOrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import com.meli.socialmeli.constants.Messages;
import com.meli.socialmeli.dto.response.ResponseDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.ConflictException;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.ISellerRepository;
import com.meli.socialmeli.repository.IUserRepository;

@ExtendWith(MockitoExtension.class)
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

    private final Integer userId = 1;
    private final Integer sellerId = 1;

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

    public void setUpExt(){
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
    }

    @Test
    @DisplayName("T-0001: User follows a Seller ")
    public void followSellerOk() {
        setUp();
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
        setUp();
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
        setUp();
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
    @DisplayName("TEST-002 - Dejar de seguir un vendedor exitosamente")
    void testUnfollowSellerOk() {
        setUp();
        // Arrange
        User mockUser = new User();
        Seller mockSeller = new Seller();
        when(userRepository.getById(userId)).thenReturn(Optional.of(mockUser));
        when(sellerRepository.getById(sellerId)).thenReturn(Optional.of(mockSeller));
        when(sellerRepository.isFollower(mockSeller, mockUser)).thenReturn(true);

        // Act
        ResponseDto response = userService.unfollowSeller(userId, sellerId);

        // Assert
        assertNotNull(response);
        assertEquals(Messages.SUCCESS_UNFOLLOW, response.getMessage());
        verify(userRepository).unfollowSeller(mockUser, mockSeller);
        verify(sellerRepository).removeFollower(mockSeller, mockUser);
    }

    @Test
    @DisplayName("TEST-002: Verificar que al dejar de seguir a un vendedor si el vendedor no existe, se lanza una excepción")
    void testUnfollowSellerException() {
        setUp();
        // Arrange
        User mockUser = new User();
        when(userRepository.getById(userId)).thenReturn(Optional.of(mockUser));
        when(sellerRepository.getById(sellerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userService.unfollowSeller(userId, sellerId));
    }

    @Test
    @DisplayName("TEST-002: Verificar que al dejar de seguir a un vendedor si el usuario no existe, se lanza una excepción")
    void testUnfollowSeller_UserNotFound() {
        setUp();
        // Arrange
        when(userRepository.getById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userService.unfollowSeller(userId, sellerId));
    }

    @Test
    @DisplayName("TEST-002: Verificar que al dejar de seguir a un vendedor si el usuario no lo sigue, se lanza una excepción")
    void testUnfollowSeller_UserNotFollowingSeller() {
        setUp();
        // Arrange
        User mockUser = new User();
        Seller mockSeller = new Seller();
        when(userRepository.getById(userId)).thenReturn(Optional.of(mockUser));
        when(sellerRepository.getById(sellerId)).thenReturn(Optional.of(mockSeller));
        when(sellerRepository.isFollower(mockSeller, mockUser)).thenReturn(false);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userService.unfollowSeller(userId, sellerId));
    }

    @Test
    void testOrderNull() {
        setUpExt();

        assertThrows(NotFoundOrderException.class, () ->
                userService.getFollowedList(1, null), Messages.ORDER_NOT_FOUND);
    }

    @Test
    void testOrderDescendente() {
        setUpExt();

        FollowedListResponseDto response = userService.getFollowedList(1, "name_desc");
        List<FollowedDto> followed = response.getFollowed();

        assertEquals("Carlos", followed.get(0).getUser_name());
        assertEquals("Beatriz", followed.get(1).getUser_name());
        assertEquals("Ana", followed.get(2).getUser_name());
    }

    @Test
    void testOrderAscendente() {
        setUpExt();

        FollowedListResponseDto response = userService.getFollowedList(1, "name_asc" );
        List<FollowedDto> followed = response.getFollowed();

        assertEquals("Ana", followed.get(0).getUser_name());
        assertEquals("Beatriz", followed.get(1).getUser_name());
        assertEquals("Carlos", followed.get(2).getUser_name());
    }

    @Test
    void testOrderInvalido() {
        setUpExt();
        assertThrows(NotFoundOrderException.class, () ->
                userService.getFollowedList(1, "El orden no es válido"), Messages.ORDER_NOT_FOUND);
    }
}

    




