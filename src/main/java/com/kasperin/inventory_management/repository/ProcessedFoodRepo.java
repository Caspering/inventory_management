package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.model.ProcessedFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ProcessedFoodRepo extends JpaRepository<ProcessedFood, Long> {

    ProcessedFood findByName(String name);

    ProcessedFood findByBarcode(long barcode);

    List<ProcessedFood> findAllProcessedFoodByNameLike(String name);


}
