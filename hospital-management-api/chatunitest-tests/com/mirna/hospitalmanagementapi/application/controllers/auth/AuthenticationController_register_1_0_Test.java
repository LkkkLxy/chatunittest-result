package com.mirna.hospitalmanagementapi.application.controllers.auth;

import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.services.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mirna.hospitalmanagementapi.domain.services.auth.jwt.TokenService;
import jakarta.validation.Valid;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationController_register_1_0_Test {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        // Arrange
        UserDTO userDTO = new UserDTO("username", "password");
        User user = new User();
        when(authService.register(userDTO)).thenReturn(user);
        // Act
        ResponseEntity<Object> response = authenticationController.register(userDTO);
        // Assert
        assertEquals(ResponseEntity.ok(user), response);
        verify(authService, times(1)).register(userDTO);
    }
}
