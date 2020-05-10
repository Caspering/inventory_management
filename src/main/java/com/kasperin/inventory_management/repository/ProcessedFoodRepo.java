package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProcessedFoodRepo
        extends JpaRepository<ProcessedFood, Long> {

    ProcessedFood findByName(String name);

    List<ProcessedFood> findAllByFoodType(FoodType foodType);


}
