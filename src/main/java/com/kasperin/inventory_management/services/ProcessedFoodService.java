package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.domain.ProcessedFood;

import java.util.List;

public interface ProcessedFoodService {

    ProcessedFood save(ProcessedFood processedFood);

    ProcessedFood findById(Long id);

    ProcessedFood findByName(String name);

    List<ProcessedFood> findAllVegan();

    List<ProcessedFood> findAllNonVegan();

    List<ProcessedFood> findAll();

    void deleteById(Long id);

}
