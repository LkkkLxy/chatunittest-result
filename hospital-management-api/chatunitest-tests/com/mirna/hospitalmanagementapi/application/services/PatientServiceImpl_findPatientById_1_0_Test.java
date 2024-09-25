package com.mirna.hospitalmanagementapi.application.services;

import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientByIdUseCase;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import jakarta.persistence.EntityNotFoundException;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientsUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.patient.SavePatientUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Address;
import com.mirna.hospitalmanagementapi.domain.services.PatientService;

class PatientServiceImpl_findPatientById_1_0_Test {

    @Mock
    private FindPatientByIdUseCase findPatientByIdUseCase;

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPatientById_PatientExists() {
        Long patientId = 1L;
        Patient patient = new Patient();
        when(findPatientByIdUseCase.execute(patientId)).thenReturn(patient);
        Patient result = patientServiceImpl.findPatientById(patientId);
        assertNotNull(result);
        assertEquals(patient, result);
        verify(findPatientByIdUseCase, times(1)).execute(patientId);
    }

    @Test
    void testFindPatientById_PatientDoesNotExist() {
        Long patientId = 2L;
        when(findPatientByIdUseCase.execute(patientId)).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> {
            patientServiceImpl.findPatientById(patientId);
        });
        verify(findPatientByIdUseCase, times(1)).execute(patientId);
    }
}
