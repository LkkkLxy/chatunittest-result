package com.mirna.hospitalmanagementapi.application.services;

import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientByIdUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.patient.SavePatientUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Address;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientsUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.services.PatientService;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImpl_updatePatient_3_0_Test {

    @Mock
    private FindPatientByIdUseCase findPatientById;

    @Mock
    private SavePatientUseCase savePatient;

    @InjectMocks
    private PatientServiceImpl patientService;

    private Patient patient;

    private PatientUpdatedDataDTO patientUpdatedDataDTO;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");
        patient.setTelephone("1234567890");
        patient.setAddress(new Address());
        AddressDTO addressDTO = new AddressDTO("123 Main St", "Downtown", "Cityville", "12345", "State", "Additional", "123");
        patientUpdatedDataDTO = new PatientUpdatedDataDTO(1L, "Jane Doe", "0987654321", addressDTO);
    }

    @Test
    public void testUpdatePatient_PatientNotFound() {
        when(findPatientById.execute(anyLong())).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> {
            patientService.updatePatient(patientUpdatedDataDTO);
        });
    }

    @Test
    public void testUpdatePatient_NameUpdated() {
        when(findPatientById.execute(anyLong())).thenReturn(patient);
        when(savePatient.execute(any(Patient.class))).thenReturn(patient);
        Patient updatedPatient = patientService.updatePatient(patientUpdatedDataDTO);
        assertEquals("Jane Doe", updatedPatient.getName());
        verify(savePatient, times(1)).execute(patient);
    }

    @Test
    public void testUpdatePatient_TelephoneUpdated() {
        when(findPatientById.execute(anyLong())).thenReturn(patient);
        when(savePatient.execute(any(Patient.class))).thenReturn(patient);
        Patient updatedPatient = patientService.updatePatient(patientUpdatedDataDTO);
        assertEquals("0987654321", updatedPatient.getTelephone());
        verify(savePatient, times(1)).execute(patient);
    }
}
