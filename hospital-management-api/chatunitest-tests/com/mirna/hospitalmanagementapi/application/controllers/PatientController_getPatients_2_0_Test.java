package com.mirna.hospitalmanagementapi.application.controllers;

import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.services.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PatientController_getPatients_2_0_Test {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPatients() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Constructor<PatientPublicDataDTO> constructor = PatientPublicDataDTO.class.getDeclaredConstructor(String.class, String.class, String.class);
        PatientPublicDataDTO patientPublicDataDTO = constructor.newInstance("John", "Doe", "1234567890");
        Page<PatientPublicDataDTO> mockPage = new PageImpl<>(Collections.singletonList(patientPublicDataDTO));
        when(patientService.findPatients(pageable)).thenReturn(mockPage);
        // Act
        ResponseEntity<Object> response = patientController.getPatients(pageable);
        // Assert
        assertEquals(ResponseEntity.ok(mockPage), response);
    }
}
