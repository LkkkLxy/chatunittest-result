package com.mirna.hospitalmanagementapi.application.services;

import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindDoctorByIdUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.SaveDoctorUseCase;
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
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindDoctorsUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Address;
import com.mirna.hospitalmanagementapi.domain.services.DoctorService;

class DoctorServiceImpl_deactivateDoctor_4_0_Test {

    @Mock
    private FindDoctorByIdUseCase findDoctorById;

    @Mock
    private SaveDoctorUseCase saveDoctor;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeactivateDoctor_DoctorExists() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        doctor.setActive(true);
        when(findDoctorById.execute(doctorId)).thenReturn(doctor);
        when(saveDoctor.execute(doctor)).thenReturn(doctor);
        Doctor result = doctorService.deactivateDoctor(doctorId);
        assertFalse(result.getActive());
        verify(findDoctorById, times(1)).execute(doctorId);
        verify(saveDoctor, times(1)).execute(doctor);
    }

    @Test
    void testDeactivateDoctor_DoctorDoesNotExist() {
        Long doctorId = 1L;
        when(findDoctorById.execute(doctorId)).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> {
            doctorService.deactivateDoctor(doctorId);
        });
        verify(findDoctorById, times(1)).execute(doctorId);
        verify(saveDoctor, never()).execute(any(Doctor.class));
    }
}
