package com.mirna.hospitalmanagementapi.application.services;

import com.mirna.hospitalmanagementapi.application.usecase.consultation.FindConsultationByIdUseCase;
import com.mirna.hospitalmanagementapi.domain.entities.Consultation;
import jakarta.persistence.EntityNotFoundException;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.application.usecase.consultation.FindConsultationByDoctorAndDateUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.consultation.FindConsultationByPatientAndDateUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.consultation.SaveConsultationUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindDoctorByIdUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindOneFreeDoctorBySpecialtyUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientByIdUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationCanceledDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.exceptions.ConsultationValidationException;
import com.mirna.hospitalmanagementapi.domain.services.ConsultationService;

class ConsultationServiceImpl_findConsultationById_1_0_Test {

    @Mock
    private FindConsultationByIdUseCase findConsultationById;

    @InjectMocks
    private ConsultationServiceImpl consultationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindConsultationById_ExistingConsultation() {
        Long id = 1L;
        Consultation consultation = new Consultation();
        when(findConsultationById.execute(id)).thenReturn(consultation);
        Consultation result = consultationService.findConsultationById(id);
        assertNotNull(result);
        assertEquals(consultation, result);
        verify(findConsultationById, times(1)).execute(id);
    }

    @Test
    void testFindConsultationById_NonExistingConsultation() {
        Long id = 2L;
        when(findConsultationById.execute(id)).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> {
            consultationService.findConsultationById(id);
        });
        verify(findConsultationById, times(1)).execute(id);
    }
}
