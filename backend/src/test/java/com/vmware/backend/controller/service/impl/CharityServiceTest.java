package com.vmware.backend.controller.service.impl;

import com.vmware.backend.model.Charity;
import com.vmware.backend.repository.CharityRepository;
import com.vmware.backend.service.impl.CharityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class CharityServiceTest {
    @Autowired
    private CharityService service;

    @MockBean
    private CharityRepository repository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        //Given
        Charity charity = new Charity("Charity Name", "Description", "Image", (double) 155, (double) 133);
        doReturn(Optional.of(charity)).when(repository).findById(charity.getId());

        //When
        Optional<Charity> returnedCharity = Optional.ofNullable(service.get(charity.getId()));

        //Then
        assertTrue(returnedCharity.isPresent(), "Charity was not found");
        assertSame(returnedCharity.get(), charity, "The charity returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        //Given
        doReturn(Optional.empty()).when(repository).findById((long) 1000);

        //When
        Optional<Charity> returnedCharity = Optional.ofNullable(service.get((long) 1000));

        //Then
        assertFalse(returnedCharity.isPresent(), "Charity should not be found");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        //Given
        Charity charity1 = new Charity("Charity Name", "Description", "Image", (double) 155, (double) 133);
        Charity charity2 = new Charity("Charity Name 2", "Description 2", "Image 2", (double) 155, (double) 133);
        Charity charity3 = new Charity("Charity Name 3", "Description 3", "Image 3", (double) 155, (double) 133);
        doReturn(Arrays.asList(charity1, charity2, charity3)).when(repository).findAll();

        //When
        List<Charity> charities = service.getAll();

        //Then
        assertEquals(3, charities.size(), "findAll should return 2 charities");
    }
}