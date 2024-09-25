package com.mirna.hospitalmanagementapi.application.usecase.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Component;
import com.auth0.jwt.algorithms.Algorithm;
import java.lang.reflect.Method;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreateJWTUseCase_execute_0_0_Test {

    @InjectMocks
    private CreateJWTUseCase createJWTUseCase;

    @Mock
    private User user;

    @Value("${api.security.token.secret}")
    private String secret;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() throws Exception {
        // Arrange
        Long userId = 1L;
        String userLogin = "testUser";
        when(user.getId()).thenReturn(userId);
        when(user.getLogin()).thenReturn(userLogin);
        // Act
        String token = createJWTUseCase.execute(user);
        // Assert
        assertNotNull(token);
        DecodedJWT decodedJWT = JWT.decode(token);
        assertEquals("Hospital-Management-API", decodedJWT.getIssuer());
        assertEquals(userLogin, decodedJWT.getSubject());
        assertEquals(userId, decodedJWT.getClaim("id").asLong());
        assertNotNull(decodedJWT.getExpiresAt());
    }

    @Test
    public void test_getExpirationDate() throws Exception {
        // Arrange
        CreateJWTUseCase spyCreateJWTUseCase = org.mockito.Mockito.spy(createJWTUseCase);
        Method getExpirationDateMethod = CreateJWTUseCase.class.getDeclaredMethod("_getExpirationDate");
        getExpirationDateMethod.setAccessible(true);
        // Act
        Instant expirationDate = (Instant) getExpirationDateMethod.invoke(spyCreateJWTUseCase);
        // Assert
        assertNotNull(expirationDate);
        assertEquals(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")), expirationDate);
    }
}
