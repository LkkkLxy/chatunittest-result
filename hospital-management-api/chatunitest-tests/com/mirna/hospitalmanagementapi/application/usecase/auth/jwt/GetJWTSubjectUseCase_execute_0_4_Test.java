package com.mirna.hospitalmanagementapi.application.usecase.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWTVerifier;

@SpringBootTest
public class GetJWTSubjectUseCase_execute_0_4_Test {

    private GetJWTSubjectUseCase getJWTSubjectUseCase;

    @Value("${jwt.secret}")
    private String secret;

    @BeforeEach
    public void setUp() {
        getJWTSubjectUseCase = new GetJWTSubjectUseCase();
        ReflectionTestUtils.setField(getJWTSubjectUseCase, "secret", secret);
    }

    @Test
    public void testExecute_ValidToken_ReturnsSubject() {
        String token = "valid.token.here";
        String subject = "testSubject";
        Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT decodedJWT = JWT.decode(token);
        when(JWT.require(algorithm).withIssuer("Hospital-Management-API").build().verify(token)).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn(subject);
        String result = getJWTSubjectUseCase.execute(token);
        assertEquals(subject, result);
    }

    @Test
    public void testExecute_InvalidToken_ThrowsException() {
        String token = "invalid.token.here";
        Algorithm algorithm = Algorithm.HMAC256(secret);
        when(JWT.require(algorithm).withIssuer("Hospital-Management-API").build().verify(token)).thenThrow(new RuntimeException("Invalid token"));
        assertThrows(RuntimeException.class, () -> getJWTSubjectUseCase.execute(token));
    }
}
