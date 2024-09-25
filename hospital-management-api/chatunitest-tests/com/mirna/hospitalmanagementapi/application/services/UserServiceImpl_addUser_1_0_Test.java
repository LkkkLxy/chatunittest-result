package com.mirna.hospitalmanagementapi.application.services;

import com.mirna.hospitalmanagementapi.application.usecase.user.SaveUserUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.application.usecase.user.FindUserByLoginUseCase;
import com.mirna.hospitalmanagementapi.domain.services.UserService;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImpl_addUser_1_0_Test {

    @Mock
    private SaveUserUseCase saveUser;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {
        // Arrange
        UserDTO userDTO = new UserDTO("username", "password");
        User expectedUser = new User(userDTO);
        when(saveUser.execute(any(User.class))).thenReturn(expectedUser);
        // Act
        User result = userServiceImpl.addUser(userDTO);
        // Assert
        assertEquals(expectedUser, result);
    }
}
