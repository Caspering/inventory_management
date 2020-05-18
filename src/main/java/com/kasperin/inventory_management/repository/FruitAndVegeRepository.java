package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.domain.FruitAndVege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitAndVegeRepository
        extends JpaRepository<FruitAndVege, Long> {

    FruitAndVege findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);

}
