package com.mirna.hospitalmanagementapi.application.services;

import com.mirna.hospitalmanagementapi.application.usecase.user.FindUserByLoginUseCase;
import com.mirna.hospitalmanagementapi.domain.repositories.auth.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.application.usecase.user.SaveUserUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.services.UserService;

class UserServiceImpl_findUserByLogin_0_0_Test {

    @Mock
    private FindUserByLoginUseCase findUserByLoginUseCase;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUserByLogin() {
        String login = "testUser";
        UserDetails expectedUserDetails = mock(UserDetails.class);
        when(findUserByLoginUseCase.execute(login)).thenReturn(expectedUserDetails);
        UserDetails result = userServiceImpl.findUserByLogin(login);
        assertEquals(expectedUserDetails, result);
        verify(findUserByLoginUseCase, times(1)).execute(login);
    }
}
