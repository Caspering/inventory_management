package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
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
    ProcessedFoodRepo processedFoodRepository;

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

        when(processedFoodRepository.save(any()))
                .thenReturn(savedProcessedFood);

        ProcessedFood result = processedFoodService.save(savedProcessedFood);

        verify(processedFoodRepository).save(any());

        assertEquals(savedProcessedFood, result);

    }

    @Test
    void findById() {
        // given
        ProcessedFood processedFood = new ProcessedFood();
        processedFood.setId(ID);

        when(processedFoodRepository.findById(anyLong())).thenReturn(Optional.of(processedFood));

        // when
        ProcessedFood result = processedFoodService.findById(ID);

        // then
        verify(processedFoodRepository).findById(eq(ID));

        assertEquals(ID, result.getId());
    }

    @Test
    void findByName() {
        // given
        ProcessedFood processedFood = new ProcessedFood();
        processedFood.setId(ID);
        processedFood.setName(NAME);

        when(processedFoodRepository.findByName(anyString())).thenReturn(Optional.of(processedFood));

        // when
        Optional<ProcessedFood> result = processedFoodService.findByName(NAME);

        // then
        verify(processedFoodRepository).findByName(eq(NAME));

        assertEquals(processedFood, result.get());
    }

    @Test
    void findAll() {
        // given
        List<ProcessedFood> processedFoods = Arrays.asList(new ProcessedFood(), new ProcessedFood());

        when(processedFoodRepository.findAll()).thenReturn(processedFoods);

        // when
        List<ProcessedFood> result = processedFoodService.findAll();

        // then
        verify(processedFoodRepository).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void deleteById() {
        // given - no conditions
        // when
        processedFoodService.deleteById(ID);
        // then
        processedFoodRepository.deleteById(eq(ID));
    }
}