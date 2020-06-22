package com.kasperin.inventory_management.repository.ItemsRepository;

import com.kasperin.inventory_management.domain.enums.FoodType;
import com.kasperin.inventory_management.domain.Items.ProcessedFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessedFoodRepo extends JpaRepository<ProcessedFood, Long> {

    boolean existsByNameIgnoreCase(String name);
    boolean existsByBarcode(String barcode);
    boolean existsByBarcodeOrNameIgnoreCase(String barcode, String name);
    boolean existsByNameContainingIgnoreCase(String name);

    ProcessedFood findByNameIgnoreCase(String name);
    ProcessedFood findByBarcodeOrNameIgnoreCase(String barcode, String name);
    ProcessedFood findByBarcode(String barcode);

    List<ProcessedFood> findAllByFoodType(FoodType foodType);
    List<ProcessedFood> findAllByNameIgnoreCase(String name);
    List<ProcessedFood> findAllByNameContainingIgnoreCase(String name);


}
