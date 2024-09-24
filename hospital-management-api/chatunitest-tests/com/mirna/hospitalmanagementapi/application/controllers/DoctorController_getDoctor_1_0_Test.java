package com.mirna.hospitalmanagementapi.application.controllers;

import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

class DoctorController_getDoctor_1_0_Test {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDoctor_DoctorFound() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        when(doctorService.findDoctorById(doctorId)).thenReturn(doctor);
        ResponseEntity<Object> response = doctorController.getDoctor(doctorId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(doctor, response.getBody());
        verify(doctorService, times(1)).findDoctorById(doctorId);
    }

    @Test
    void testGetDoctor_DoctorNotFound() {
        Long doctorId = 1L;
        when(doctorService.findDoctorById(doctorId)).thenReturn(null);
        ResponseEntity<Object> response = doctorController.getDoctor(doctorId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(null, response.getBody());
        verify(doctorService, times(1)).findDoctorById(doctorId);
    }
}
