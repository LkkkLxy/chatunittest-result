package com.mirna.hospitalmanagementapi.application.services.auth.jwt;

import com.mirna.hospitalmanagementapi.application.usecase.auth.jwt.CreateJWTUseCase;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.application.usecase.auth.jwt.GetJWTSubjectUseCase;
import com.mirna.hospitalmanagementapi.domain.services.auth.jwt.TokenService;

class TokenServiceImpl_generateToken_0_0_Test {

    @Mock
    private CreateJWTUseCase createJWT;

    @InjectMocks
    private TokenServiceImpl tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateToken() {
        User user = new User();
        user.setId(1L);
        user.setLogin("testUser");
        String expectedToken = "generatedToken";
        when(createJWT.execute(user)).thenReturn(expectedToken);
        String actualToken = tokenService.generateToken(user);
        assertEquals(expectedToken, actualToken);
        verify(createJWT, times(1)).execute(user);
    }
}
