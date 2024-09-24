package com.mirna.hospitalmanagementapi.application.controllers;

import com.mirna.hospitalmanagementapi.domain.entities.Consultation;
import com.mirna.hospitalmanagementapi.domain.services.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;
import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationCanceledDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationDTO;
import com.mirna.hospitalmanagementapi.domain.exceptions.ConsultationValidationException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

class ConsultationController_getConsultation_1_0_Test {

    @Mock
    private ConsultationService consultationService;

    @InjectMocks
    private ConsultationController consultationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetConsultation_Success() {
        Long id = 1L;
        Consultation consultation = new Consultation();
        when(consultationService.findConsultationById(id)).thenReturn(consultation);
        ResponseEntity<Object> response = consultationController.getConsultation(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(consultation, response.getBody());
    }

    @Test
    void testGetConsultation_NotFound() {
        Long id = 2L;
        when(consultationService.findConsultationById(id)).thenReturn(null);
        ResponseEntity<Object> response = consultationController.getConsultation(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(null, response.getBody());
    }
}
