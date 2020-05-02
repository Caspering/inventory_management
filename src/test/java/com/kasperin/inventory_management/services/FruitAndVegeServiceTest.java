package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.mapper.FruitAndVegeMapper;
import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class FruitAndVegeServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Apple";
    FruitAndVegeService fruitAndVegeService;

    @Mock
    FruitAndVegeRepository fruitAndVegeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        fruitAndVegeService = new FruitAndVegeServiceImpl(FruitAndVegeMapper.INSTANCE, fruitAndVegeRepository);
    }

    @Test
    public void findAll() throws Exception {

        //given
        List<FruitAndVege> categories = Arrays.asList(new FruitAndVege(), new FruitAndVege(), new FruitAndVege());

        when(fruitAndVegeRepository.findAll()).thenReturn(categories);

        //when
        List<FruitAndVegeDTO> fruitAndVegeDTOS = fruitAndVegeService.findAll();

        //then
        assertEquals(3, fruitAndVegeDTOS.size());

    }

    @Test
    public void findByName() throws Exception {

        //given
        FruitAndVege fruitAndVege = new FruitAndVege();
        fruitAndVege.setId(ID);
        fruitAndVege.setName(NAME);

        when(fruitAndVegeRepository.findByName(anyString())).thenReturn(fruitAndVege);

        //when
        FruitAndVegeDTO fruitAndVegeDTO = fruitAndVegeService.findByName(NAME);

        //then
        assertEquals(ID, fruitAndVegeDTO.getId());
        assertEquals(NAME, fruitAndVegeDTO.getName());

    }
}