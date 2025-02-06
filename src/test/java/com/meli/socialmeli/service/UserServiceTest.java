package com.meli.socialmeli.service;

import com.meli.socialmeli.constants.Messages;
import com.meli.socialmeli.dto.response.ResponseDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.exception.NotFoundException;
import com.meli.socialmeli.repository.ISellerRepository;
import com.meli.socialmeli.repository.IUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private ISellerRepository sellerRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private final Integer userId = 1;
    private final Integer sellerId = 1;

    @Test
    @DisplayName("TEST-002 - Dejar de seguir un vendedor exitosamente")
    void testUnfollowSellerOk() {
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
        // Arrange
        when(userRepository.getById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userService.unfollowSeller(userId, sellerId));
    }

    @Test
    @DisplayName("TEST-002: Verificar que al dejar de seguir a un vendedor si el usuario no lo sigue, se lanza una excepción")
    void testUnfollowSeller_UserNotFollowingSeller() {
        // Arrange
        User mockUser = new User();
        Seller mockSeller = new Seller();
        when(userRepository.getById(userId)).thenReturn(Optional.of(mockUser));
        when(sellerRepository.getById(sellerId)).thenReturn(Optional.of(mockSeller));
        when(sellerRepository.isFollower(mockSeller, mockUser)).thenReturn(false);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userService.unfollowSeller(userId, sellerId));
    }



}
