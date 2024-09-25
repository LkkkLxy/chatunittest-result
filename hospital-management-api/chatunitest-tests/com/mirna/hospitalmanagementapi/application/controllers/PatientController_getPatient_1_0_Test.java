package com.mirna.hospitalmanagementapi.application.controllers;

import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
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
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientUpdatedDataDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

class PatientController_getPatient_1_0_Test {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPatient_PatientFound() {
        Long patientId = 1L;
        Patient patient = new Patient();
        when(patientService.findPatientById(patientId)).thenReturn(patient);
        ResponseEntity<Object> response = patientController.getPatient(patientId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).findPatientById(patientId);
    }

    @Test
    void testGetPatient_PatientNotFound() {
        Long patientId = 1L;
        when(patientService.findPatientById(patientId)).thenReturn(null);
        ResponseEntity<Object> response = patientController.getPatient(patientId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(null, response.getBody());
        verify(patientService, times(1)).findPatientById(patientId);
    }
}
