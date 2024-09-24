package com.mirna.hospitalmanagementapi.application.services.auth.jwt;

import com.mirna.hospitalmanagementapi.application.usecase.auth.jwt.GetJWTSubjectUseCase;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.application.usecase.auth.jwt.CreateJWTUseCase;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.services.auth.jwt.TokenService;

class TokenServiceImpl_getTokenSubject_1_0_Test {

    @Mock
    private GetJWTSubjectUseCase getJWTSubject;

    @InjectMocks
    private TokenServiceImpl tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTokenSubject() {
        String token = "validToken";
        String expectedSubject = "user123";
        when(getJWTSubject.execute(token)).thenReturn(expectedSubject);
        String actualSubject = tokenService.getTokenSubject(token);
        assertEquals(expectedSubject, actualSubject);
    }
}
