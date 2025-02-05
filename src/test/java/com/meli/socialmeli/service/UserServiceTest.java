package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.FollowedDto;
import com.meli.socialmeli.dto.FollowedListResponseDto;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.entity.User;
import com.meli.socialmeli.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

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
    }

    @Test
    void testOrderAscendente() {
        FollowedListResponseDto response = userService.getFollowedList(1, null );
        List<FollowedDto> followed = response.getFollowed();

        assertEquals("Ana", followed.get(0).getUser_name());
        assertEquals("Beatriz", followed.get(1).getUser_name());
        assertEquals("Carlos", followed.get(2).getUser_name());
    }

    @Test
    void testOrderDescendente() {
        FollowedListResponseDto response = userService.getFollowedList(1, "name_desc");
        List<FollowedDto> followed = response.getFollowed();

        assertEquals("Carlos", followed.get(0).getUser_name());
        assertEquals("Beatriz", followed.get(1).getUser_name());
        assertEquals("Ana", followed.get(2).getUser_name());
    }
}
