package com.mirna.hospitalmanagementapi.application.services.auth;

import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.services.UserService;
import com.mirna.hospitalmanagementapi.domain.services.auth.AuthService;

class AuthServiceImpl_login_0_0_Test {

    @Mock
    private AuthenticationManager manager;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        UserDTO userDTO = new UserDTO("user", "password");
        UsernamePasswordAuthenticationToken expectedToken = new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.password());
        Authentication expectedAuthentication = mock(Authentication.class);
        when(manager.authenticate(expectedToken)).thenReturn(expectedAuthentication);
        Authentication result = authService.login(userDTO);
        assertEquals(expectedAuthentication, result);
        verify(manager, times(1)).authenticate(expectedToken);
    }

    @Test
    void testLoginFailure() {
        UserDTO userDTO = new UserDTO("user", "password");
        UsernamePasswordAuthenticationToken expectedToken = new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.password());
        when(manager.authenticate(expectedToken)).thenThrow(new RuntimeException("Authentication failed"));
        try {
            authService.login(userDTO);
        } catch (RuntimeException e) {
            assertEquals("Authentication failed", e.getMessage());
        }
        verify(manager, times(1)).authenticate(expectedToken);
    }
}
