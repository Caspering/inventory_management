package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProcessedFoodServiceImplTest {
    public static final Long ID = 1L;
    public static final Long ID2 = 2L;
    public static final String NAME = "Chip";
    public static final String NAME2 = "Burger";
    public static final String BARCODE = "123456";
    public static final String BARCODE2 = "789012";
    public static final double PRICE = 1.9;
    public static final double PRICE2 = 1.9;
    public static final FoodType FOODTYPE = FoodType.VEGAN;
    public static final FoodType FOODTYPE2 = FoodType.NONVEGAN;

    @InjectMocks
    ProcessedFoodServiceImpl processedFoodService;

    @Mock
    ProcessedFoodRepo processedFoodRepo;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void save() {
        //given
        ProcessedFood savedProcessedFood = new ProcessedFood();
        savedProcessedFood.setId(ID);
        savedProcessedFood.setName(NAME);
        savedProcessedFood.setBarcode(BARCODE);
        savedProcessedFood.setPrice(PRICE);
        savedProcessedFood.setFoodType(FOODTYPE);

        when(processedFoodRepo.save(any()))
                .thenReturn(savedProcessedFood);

        ProcessedFood result = processedFoodService.save(savedProcessedFood);

        verify(processedFoodRepo).save(any());

        assertEquals(savedProcessedFood, result);

    }

    @Test
    void findById() {
        // given
        ProcessedFood processedFood = new ProcessedFood();
        processedFood.setId(ID);

        when(processedFoodRepo.findById(anyLong())).thenReturn(Optional.of(processedFood));

        // when
        ProcessedFood result = processedFoodService.findById(ID);

        // then
        verify(processedFoodRepo).findById(eq(ID));

        assertEquals(ID, processedFood.getId());
    }

    @Test
    void findByName() {
    }

    @Test
    void findAllByFoodType() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }
}