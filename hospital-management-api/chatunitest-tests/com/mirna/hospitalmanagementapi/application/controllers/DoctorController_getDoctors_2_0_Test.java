package com.mirna.hospitalmanagementapi.application.controllers;

import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.services.DoctorService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@ExtendWith(MockitoExtension.class)
public class DoctorController_getDoctors_2_0_Test {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    private Pageable pageable;

    private Page<DoctorPublicDataDTO> doctorsPage;

    @BeforeEach
    public void setUp() {
        pageable = PageRequest.of(0, 10);
        doctorsPage = new PageImpl<>(Collections.singletonList(new DoctorPublicDataDTO("John", "Doe", "123456789", null)));
    }

    @Test
    public void testGetDoctors() {
        when(doctorService.findDoctors(pageable)).thenReturn(doctorsPage);
        ResponseEntity<Object> response = doctorController.getDoctors(pageable);
        assertEquals(ResponseEntity.ok(doctorsPage), response);
    }
}
