package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.model.FruitAndVege;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class FruitAndVegeServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Banana";
    public static final String BARCODE = "123456";
    public static final double PRICE = 1.9;

    FruitAndVegeService fruitAndVegeService;

    @Mock
    FruitAndVegeRepository fruitAndVegeRepository;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        fruitAndVegeService = new FruitAndVegeServiceImpl(fruitAndVegeRepository);
    }

    @Test
    void createNewFruitAndVege() throws Exception{
        //given
        FruitAndVege savedFruitAndVege = new FruitAndVege();
        savedFruitAndVege.setId(ID);
        savedFruitAndVege.setName(NAME);
        savedFruitAndVege.setBarcode(BARCODE);
        savedFruitAndVege.setPrice(PRICE);

        when(fruitAndVegeRepository.save(any(FruitAndVege.class)))
                .thenReturn(savedFruitAndVege);
    }

    @Test
    void getFruitAndVegeById() {
        //given
        FruitAndVege fruitAndVege = new FruitAndVege();
        fruitAndVege.setId(ID);
        fruitAndVege.setName(NAME);
        fruitAndVege.setBarcode(BARCODE);
        fruitAndVege.setPrice(PRICE);

        when(fruitAndVegeRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.ofNullable(fruitAndVege));
    }

    @Test
    void getFruitAndVegeByName() throws Exception{
        //given
        FruitAndVege fruitAndVege = new FruitAndVege();
        fruitAndVege.setId(ID);
        fruitAndVege.setName(NAME);
        fruitAndVege.setBarcode(BARCODE);
        fruitAndVege.setPrice(PRICE);

        when(fruitAndVegeRepository.findByName(anyString()))
                .thenReturn(fruitAndVege);
    }

    @Test
    void getAllFruitAndVeges() throws Exception {
        //given
        List<FruitAndVege> fruitAndVeges =
                Arrays.asList(new FruitAndVege(), new FruitAndVege());

        when (fruitAndVegeRepository.findAll()).thenReturn(fruitAndVeges);
    }

//    @Test
//    void updateFruitAndVege() {
//    }
//
//    @Test
//    void deleteFruitAndVegeById() throws Exception{
//
//        fruitAndVegeRepository.deleteFruitAndVegeById(ID);
//        verify(fruitAndVegeRepository, times(1)
//                .deleteFruitAndVegeById(anyLong()));
//    }
}