package com.mirna.hospitalmanagementapi.application.services;

import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientsUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.List;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientByIdUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.patient.SavePatientUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Address;
import com.mirna.hospitalmanagementapi.domain.services.PatientService;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImpl_findPatients_2_0_Test {

    @Mock
    private FindPatientsUseCase findPatientsUseCase;

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    private Pageable pageable;

    private Page<Patient> patientPage;

    private List<Patient> patientList;

    @BeforeEach
    public void setUp() {
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();
        patientList = Arrays.asList(patient1, patient2);
        patientPage = new PageImpl<>(patientList);
        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void testFindPatients() {
        when(findPatientsUseCase.execute(pageable)).thenReturn(patientPage);
        Page<PatientPublicDataDTO> result = patientServiceImpl.findPatients(pageable);
        assertEquals(patientList.size(), result.getContent().size());
        assertEquals(PatientPublicDataDTO.class, result.getContent().get(0).getClass());
    }
}
