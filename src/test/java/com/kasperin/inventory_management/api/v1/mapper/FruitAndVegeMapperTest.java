package com.kasperin.inventory_management.api.v1.mapper;

import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.domain.FruitAndVege;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FruitAndVegeMapperTest {

    FruitAndVegeMapper fruitAndVegeMapper = FruitAndVegeMapper.INSTANCE;

    @Test
    void fruitAndVegeToFruitAndVegeDTO() throws Exception{
        //given
        FruitAndVege fruitAndVege = new FruitAndVege();
        fruitAndVege.setBarcode("12345");
        fruitAndVege.setName("apple");
        fruitAndVege.setPrice(1.2);
        fruitAndVege.setInStockQuantity(4);

        //when
        FruitAndVegeDTO fruitAndVegeDTO = FruitAndVegeMapper.INSTANCE.fruitAndVegeToFruitAndVegeDTO(fruitAndVege);

        //then
        assertThat(fruitAndVegeDTO).isNotNull();
        assertThat(fruitAndVegeDTO.getBarcode()).isEqualTo("12345");
        assertThat(fruitAndVegeDTO.getName()).isEqualTo("apple");
        assertThat(fruitAndVegeDTO.getPrice()).isEqualTo(1.2);
        assertThat(fruitAndVegeDTO.getInStockQuantity()).isEqualTo(4);

    }

}