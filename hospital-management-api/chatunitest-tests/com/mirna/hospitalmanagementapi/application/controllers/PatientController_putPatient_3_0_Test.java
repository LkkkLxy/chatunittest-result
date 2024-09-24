package com.mirna.hospitalmanagementapi.application.controllers;

import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.services.PatientService;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientPublicDataDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PatientController_putPatient_3_0_Test {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPutPatient_Success() {
        // Arrange
        PatientUpdatedDataDTO patientUpdatedDataDTO = new PatientUpdatedDataDTO(1L, "John", "Doe", null);
        Patient patient = new Patient();
        when(patientService.updatePatient(patientUpdatedDataDTO)).thenReturn(patient);
        // Act
        ResponseEntity<Object> response = patientController.putPatient(patientUpdatedDataDTO);
        // Assert
        assertEquals(ResponseEntity.ok(patient), response);
        verify(patientService, times(1)).updatePatient(patientUpdatedDataDTO);
    }

    @Test
    void testPutPatient_NullPatient() {
        // Arrange
        PatientUpdatedDataDTO patientUpdatedDataDTO = new PatientUpdatedDataDTO(1L, "John", "Doe", null);
        when(patientService.updatePatient(patientUpdatedDataDTO)).thenReturn(null);
        // Act
        ResponseEntity<Object> response = patientController.putPatient(patientUpdatedDataDTO);
        // Assert
        assertEquals(ResponseEntity.ok(null), response);
        verify(patientService, times(1)).updatePatient(patientUpdatedDataDTO);
    }
}
