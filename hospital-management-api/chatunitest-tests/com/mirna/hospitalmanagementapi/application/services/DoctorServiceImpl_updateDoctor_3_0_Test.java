package com.mirna.hospitalmanagementapi.application.services;

import com.mirna.hospitalmanagementapi.application.usecase.doctor.SaveDoctorUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Address;
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
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindDoctorByIdUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindDoctorsUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.services.DoctorService;

class DoctorServiceImpl_updateDoctor_3_0_Test {

    @Mock
    private SaveDoctorUseCase saveDoctor;

    @Mock
    private FindDoctorByIdUseCase findDoctorById;

    @InjectMocks
    private DoctorServiceImpl doctorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateDoctor_DoctorNotFound() {
        DoctorUpdatedDataDTO doctorUpdatedDataDTO = new DoctorUpdatedDataDTO(1L, "New Name", "New Telephone", new AddressDTO("Street", "Neighborhood", "City", "ZipCode", "State", "AdditionalDetails", "HouseNumber"));
        when(findDoctorById.execute(doctorUpdatedDataDTO.id())).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> {
            doctorServiceImpl.updateDoctor(doctorUpdatedDataDTO);
        });
    }

    @Test
    void testUpdateDoctor_NameUpdated() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Old Name");
        doctor.setTelephone("Old Telephone");
        doctor.setAddress(new Address());
        DoctorUpdatedDataDTO doctorUpdatedDataDTO = new DoctorUpdatedDataDTO(1L, "New Name", null, null);
        when(findDoctorById.execute(doctorUpdatedDataDTO.id())).thenReturn(doctor);
        when(saveDoctor.execute(any(Doctor.class))).thenAnswer(i -> i.getArguments()[0]);
        Doctor updatedDoctor = doctorServiceImpl.updateDoctor(doctorUpdatedDataDTO);
        assertEquals("New Name", updatedDoctor.getName());
        assertEquals("Old Telephone", updatedDoctor.getTelephone());
    }

    @Test
    void testUpdateDoctor_PartialAddressUpdated() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Old Name");
        doctor.setTelephone("Old Telephone");
        Address oldAddress = new Address();
        oldAddress.setStreet("Old Street");
        oldAddress.setNeighborhood("Old Neighborhood");
        oldAddress.setCity("Old City");
        oldAddress.setZipCode("Old ZipCode");
        oldAddress.setState("Old State");
        oldAddress.setAdditionalDetails("Old AdditionalDetails");
        oldAddress.setHouseNumber("Old HouseNumber");
        doctor.setAddress(oldAddress);
        AddressDTO addressDTO = new AddressDTO(null, "New Neighborhood", null, null, "New State", null, "New HouseNumber");
        DoctorUpdatedDataDTO doctorUpdatedDataDTO = new DoctorUpdatedDataDTO(1L, null, null, addressDTO);
        when(findDoctorById.execute(doctorUpdatedDataDTO.id())).thenReturn(doctor);
        when(saveDoctor.execute(any(Doctor.class))).thenAnswer(i -> i.getArguments()[0]);
        Doctor updatedDoctor = doctorServiceImpl.updateDoctor(doctorUpdatedDataDTO);
        Address updatedAddress = updatedDoctor.getAddress();
        assertEquals("Old Street", updatedAddress.getStreet());
        assertEquals("New Neighborhood", updatedAddress.getNeighborhood());
        assertEquals("Old City", updatedAddress.getCity());
        assertEquals("Old ZipCode", updatedAddress.getZipCode());
        assertEquals("New State", updatedAddress.getState());
        assertEquals("Old AdditionalDetails", updatedAddress.getAdditionalDetails());
        assertEquals("New HouseNumber", updatedAddress.getHouseNumber());
    }
}
