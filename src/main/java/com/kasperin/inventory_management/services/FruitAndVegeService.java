package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
public interface FruitAndVegeService {

    FruitAndVegeDTO createNewFruitAndVege(@Valid FruitAndVegeDTO fruitAndVegeDTO);

    Optional<FruitAndVege> updateById(Long id, @Valid FruitAndVege fruitAndVege);

    FruitAndVegeDTO saveAndReturnDTO(@Valid FruitAndVege fruitAndVege);

    List<FruitAndVege> findAllInStock();

    FruitAndVegeDTO findById(Long id);

    FruitAndVegeDTO findByName(String name);

    List<FruitAndVege> findAll();

    List<FruitAndVege> findAllByName(String name);

    void deleteById(Long id);

}
