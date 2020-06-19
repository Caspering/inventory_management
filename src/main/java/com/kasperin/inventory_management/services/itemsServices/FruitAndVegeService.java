package com.kasperin.inventory_management.services.itemsServices;

import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
public interface FruitAndVegeService {

    FruitAndVegeDTO createNewFruitAndVege(@Valid FruitAndVegeDTO fruitAndVegeDTO) throws Exception;

    Optional<FruitAndVege> updateById(Long id, @Valid FruitAndVege fruitAndVege);

    FruitAndVegeDTO saveAndReturnDTO(@Valid FruitAndVege fruitAndVege) throws Exception;

    List<FruitAndVege> findAllInStock();

    FruitAndVegeDTO findById(Long id);

    FruitAndVegeDTO findByName(String name);

    List<FruitAndVege> findAll();

    List<FruitAndVege> findAllByNameContaining(String name);

    void deleteById(Long id);

}
