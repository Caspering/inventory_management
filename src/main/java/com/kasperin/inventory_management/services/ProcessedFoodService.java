package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
public interface ProcessedFoodService {

    ProcessedFood save(@Valid ProcessedFood processedFood);

    Optional<ProcessedFood> updateById(Long id, @Valid ProcessedFood processedFood);

    Optional<ProcessedFood> findById(Long id);

    ProcessedFood  findByName(String name);

    List<ProcessedFood> findAll();

    List<ProcessedFood> findByType(FoodType foodType);

    void deleteById(Long id);

}
