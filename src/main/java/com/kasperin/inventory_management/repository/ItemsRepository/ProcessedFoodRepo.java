package com.kasperin.inventory_management.repository.ItemsRepository;

import com.kasperin.inventory_management.domain.Items.FoodType;
import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.domain.Items.ProcessedFood;
import com.kasperin.inventory_management.domain.Items.Stationary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessedFoodRepo
        extends JpaRepository<ProcessedFood, Long> {

    ProcessedFood findByNameIgnoreCase(String name);
    List<ProcessedFood> findAllByFoodType(FoodType foodType);
    List<ProcessedFood> findAllByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
    boolean existsByBarcode(String barcode);
    boolean existsByBarcodeOrNameIgnoreCase(String barcode, String name);
    ProcessedFood findByBarcodeOrNameIgnoreCase(String barcode, String name);


    ProcessedFood findByBarcode(String barcode);
}