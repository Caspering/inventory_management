package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.mapper.FruitAndVegeMapper;
import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    ProcessedFoodService processedFoodService;

    @Mock
    ProcessedFoodRepo processedFoodRepo;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

    }

//    @Test
//    void save() {
//        //given
//        ProcessedFood savedProcessedFood = new ProcessedFood();
//        savedProcessedFood.setId(ID);
//        savedProcessedFood.setName(NAME);
//        savedProcessedFood.setBarcode(BARCODE);
//        savedProcessedFood.setPrice(PRICE);
//        savedProcessedFood.setFoodType(FOODTYPE);
//
//        when(ProcessedFoodRepo.save(any()))
//                .thenReturn(savedProcessedFood);
//
//        ProcessedFood result = ProcessedFoodService.save(savedProcessedFood);
//
//        verify(ProcessedFoodRepo).save(any());
//
//        assertEquals(savedProcessedFood, result);
//
//    }

    @Test
    void findById() {
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