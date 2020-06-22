package com.kasperin.inventory_management.services.itemsServices;

import com.kasperin.inventory_management.domain.enums.FoodType;
import com.kasperin.inventory_management.domain.Items.ProcessedFood;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
public interface ProcessedFoodService {

    ProcessedFood save(@Valid ProcessedFood processedFood) throws Exception;

   /* @Validated(OnCreate.class)
    ProcessedFood save(ProcessedFood processedFood);*/

    Optional<ProcessedFood> updateById(Long id, @Valid ProcessedFood newProcessedFood);

    Optional<ProcessedFood> findById(Long id);

    ProcessedFood  findByName(String name);

    List<ProcessedFood> findAllByName(String name);

    List<ProcessedFood> findAllByNameContaining(String name);

    List<ProcessedFood> findAll();

    List<ProcessedFood> findAllInStock();

    List<ProcessedFood> findByType(FoodType foodType);

    void deleteById(Long id);

}
