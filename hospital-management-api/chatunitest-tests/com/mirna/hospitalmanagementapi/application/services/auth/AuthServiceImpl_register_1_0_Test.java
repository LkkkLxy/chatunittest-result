package com.mirna.hospitalmanagementapi.application.services.auth;

import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.domain.services.auth.AuthService;

class AuthServiceImpl_register_1_0_Test {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        // Arrange
        UserDTO userDTO = new UserDTO("testUser", "password");
        String encodedPassword = "encodedPassword";
        User expectedUser = new User();
        expectedUser.setLogin("testUser");
        expectedUser.setPassword(encodedPassword);
        when(passwordEncoder.encode(userDTO.password())).thenReturn(encodedPassword);
        when(userService.addUser(any(UserDTO.class))).thenReturn(expectedUser);
        // Act
        User resultUser = authService.register(userDTO);
        // Assert
        assertEquals(expectedUser.getLogin(), resultUser.getLogin());
        assertEquals(expectedUser.getPassword(), resultUser.getPassword());
    }
}
