package com.kasperin.inventory_management.api.v1.mapper;

import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FruitAndVegeMapper {

    FruitAndVegeMapper INSTANCE = Mappers.getMapper(FruitAndVegeMapper.class);

    FruitAndVegeDTO fruitAndVegeToFruitAndVegeDTO(FruitAndVege fruitAndVege);

    FruitAndVege fruitAndVegeDTOtoFruitAndVege(FruitAndVegeDTO fruitAndVegeDTO);

}
