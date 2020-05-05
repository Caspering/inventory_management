package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import com.kasperin.inventory_management.repository.StationaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StationaryServiceImplTest {

    public static final Long ID = 1L;
    public static final Long ID2 = 2L;
    public static final String NAME = "Glue";
    public static final String NAME2 = "Pencil";
    public static final String BARCODE = "123456";
    public static final String BARCODE2 = "789012";
    public static final double PRICE = 0.5;
    public static final double PRICE2 = 0.3;

    @InjectMocks
    StationaryServiceImpl stationaryService;

    @Mock
    StationaryRepository stationaryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        // given
        List<Stationary> stationaryList = Arrays
                .asList(new Stationary(), new Stationary());

        when(stationaryRepository.findAll())
                .thenReturn(stationaryList);

        // when
        List<Stationary> result = stationaryService.findAll();

        // then
        verify(stationaryRepository).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void findByName() {
        //given
        Stationary stationary = new Stationary();
        stationary.setId(ID);
        stationary.setBarcode(BARCODE);
        stationary.setName(NAME);
        stationary.setPrice(PRICE);

        when(stationaryRepository.findByName(anyString()))
                .thenReturn(Optional.of(stationary));

        //when
        Optional<Stationary> result = stationaryService
                .findByName(NAME);

        //then
        verify(stationaryRepository).findByName(eq(NAME));

        assertEquals(stationary, result.get());
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void post() {
    }

    @Test
    void deleteById() {
    }
}