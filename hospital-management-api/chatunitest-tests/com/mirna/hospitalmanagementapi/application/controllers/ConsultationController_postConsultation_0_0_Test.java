package com.mirna.hospitalmanagementapi.application.controllers;

import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Consultation;
import com.mirna.hospitalmanagementapi.domain.exceptions.ConsultationValidationException;
import com.mirna.hospitalmanagementapi.domain.services.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import static org.mockito.ArgumentMatchers.any;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationCanceledDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ConsultationController_postConsultation_0_0_Test {

    @Mock
    private ConsultationService consultationService;

    @InjectMocks
    private ConsultationController consultationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostConsultation_Success() throws ConsultationValidationException {
        ConsultationDTO consultationDTO = new ConsultationDTO(1L, 1L, LocalDateTime.now(), null);
        Consultation consultation = new Consultation();
        consultation.setId(1L);
        when(consultationService.addConsultation(any(ConsultationDTO.class))).thenReturn(consultation);
        ResponseEntity<Object> response = consultationController.postConsultation(consultationDTO);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(URI.create("/api/v1.0/consultations/1"), response.getHeaders().getLocation());
        assertEquals(consultation, response.getBody());
    }

    @Test
    void testPostConsultation_ValidationException() throws ConsultationValidationException {
        ConsultationDTO consultationDTO = new ConsultationDTO(1L, 1L, LocalDateTime.now(), null);
        when(consultationService.addConsultation(any(ConsultationDTO.class))).thenThrow(new ConsultationValidationException("Validation failed"));
        assertThrows(ConsultationValidationException.class, () -> {
            consultationController.postConsultation(consultationDTO);
        });
    }
}
