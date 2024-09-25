package com.mirna.hospitalmanagementapi.application.services;

import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindDoctorsUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.List;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.SaveDoctorUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindDoctorByIdUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Address;
import com.mirna.hospitalmanagementapi.domain.services.DoctorService;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceImpl_findDoctors_2_0_Test {

    @Mock
    private FindDoctorsUseCase findDoctorsUseCase;

    @InjectMocks
    private DoctorServiceImpl doctorServiceImpl;

    private Pageable pageable;

    private Doctor doctor1;

    private Doctor doctor2;

    private Page<Doctor> doctorPage;

    @BeforeEach
    public void setUp() {
        doctor1 = new Doctor();
        doctor2 = new Doctor();
        List<Doctor> doctorList = Arrays.asList(doctor1, doctor2);
        doctorPage = new PageImpl<>(doctorList);
    }

    @Test
    public void testFindDoctors() {
        when(findDoctorsUseCase.execute(pageable)).thenReturn(doctorPage);
        Page<DoctorPublicDataDTO> result = doctorServiceImpl.findDoctors(pageable);
        assertEquals(2, result.getContent().size());
        assertEquals(DoctorPublicDataDTO.class, result.getContent().get(0).getClass());
        assertEquals(DoctorPublicDataDTO.class, result.getContent().get(1).getClass());
    }
}
