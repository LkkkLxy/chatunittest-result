package com.mirna.hospitalmanagementapi.application.services;

import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientByIdUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.patient.SavePatientUseCase;
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
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Address;
import com.mirna.hospitalmanagementapi.domain.services.PatientService;

class PatientServiceImpl_deactivatePatient_4_0_Test {

    @Mock
    private FindPatientByIdUseCase findPatientById;

    @Mock
    private SavePatientUseCase savePatient;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeactivatePatient_PatientExists() {
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);
        patient.setActive(true);
        when(findPatientById.execute(patientId)).thenReturn(patient);
        when(savePatient.execute(patient)).thenReturn(patient);
        Patient result = patientService.deactivatePatient(patientId);
        assertFalse(result.getActive());
        verify(findPatientById, times(1)).execute(patientId);
        verify(savePatient, times(1)).execute(patient);
    }

    @Test
    void testDeactivatePatient_PatientDoesNotExist() {
        Long patientId = 1L;
        when(findPatientById.execute(patientId)).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> {
            patientService.deactivatePatient(patientId);
        });
        verify(findPatientById, times(1)).execute(patientId);
        verify(savePatient, never()).execute(any(Patient.class));
    }
}
