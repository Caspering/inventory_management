package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.model.FruitAndVege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitAndVegeRepository
        extends JpaRepository<FruitAndVege, Long> {


    FruitAndVege findByName(String name);


}
