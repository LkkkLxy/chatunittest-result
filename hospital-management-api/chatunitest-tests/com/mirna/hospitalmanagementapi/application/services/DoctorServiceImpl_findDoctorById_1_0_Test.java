package com.mirna.hospitalmanagementapi.application.services;

import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindDoctorByIdUseCase;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import jakarta.persistence.EntityNotFoundException;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.SaveDoctorUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindDoctorsUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Address;
import com.mirna.hospitalmanagementapi.domain.services.DoctorService;

class DoctorServiceImpl_findDoctorById_1_0_Test {

    @Mock
    private FindDoctorByIdUseCase findDoctorByIdUseCase;

    @InjectMocks
    private DoctorServiceImpl doctorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindDoctorById_DoctorExists() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        when(findDoctorByIdUseCase.execute(doctorId)).thenReturn(doctor);
        Doctor result = doctorServiceImpl.findDoctorById(doctorId);
        assertNotNull(result);
        assertEquals(doctorId, result.getId());
    }

    @Test
    void testFindDoctorById_DoctorDoesNotExist() {
        Long doctorId = 2L;
        when(findDoctorByIdUseCase.execute(doctorId)).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> {
            doctorServiceImpl.findDoctorById(doctorId);
        });
    }
}
