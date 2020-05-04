package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.ProcessedFood;

import java.util.List;
import java.util.Optional;

public interface ProcessedFoodService {

    ProcessedFood save(ProcessedFood processedFood);

    ProcessedFood findById(Long id);

    Optional<ProcessedFood> findByName(String name);

    List<ProcessedFood> findAllVegan();

    List<ProcessedFood> findAllNonVegan();

    List<ProcessedFood> findAll();

    void deleteById(Long id);

}
