package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.model.FruitAndVege;
import com.kasperin.inventory_management.model.ProcessedFood;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface FruitAndVegeRepository
        extends CrudRepository<FruitAndVege, Long> {


    FruitAndVege findByName(String name);

    FruitAndVege findByBarcode(long barcode);

    List<FruitAndVege> findAllFruitAndVegeByNameLike(String name);


}
