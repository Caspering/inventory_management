package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;

import java.util.List;
import java.util.Optional;

public interface ProcessedFoodService {

    ProcessedFood save(ProcessedFood processedFood);

    Optional<ProcessedFood> findById(Long id);

    ProcessedFood  findByName(String name);

    List<ProcessedFood> findAll();

    List<ProcessedFood> findByType(FoodType foodType);

    void deleteById(Long id);

}
