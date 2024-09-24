package com.mirna.hospitalmanagementapi.application.services.auth;

import com.mirna.hospitalmanagementapi.domain.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

class UserDetailsServiceImpl_loadUserByUsername_0_0_Test {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        String username = "testUser";
        UserDetails userDetails = mock(UserDetails.class);
        when(userService.findUserByLogin(username)).thenReturn(userDetails);
        UserDetails result = userDetailsService.loadUserByUsername(username);
        assertEquals(userDetails, result);
        verify(userService, times(1)).findUserByLogin(username);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        String username = "nonExistentUser";
        when(userService.findUserByLogin(username)).thenThrow(new UsernameNotFoundException("User not found"));
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(username);
        });
        verify(userService, times(1)).findUserByLogin(username);
    }
}
