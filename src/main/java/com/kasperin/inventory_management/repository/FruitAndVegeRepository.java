package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.model.FruitAndVege;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FruitAndVegeRepository
        extends CrudRepository<FruitAndVege, Long> {


    FruitAndVege findByName(String name);

    FruitAndVege findByBarcode(String barcode);

    List<FruitAndVege> findAllFruitAndVegeByNameLike(String name);


}
