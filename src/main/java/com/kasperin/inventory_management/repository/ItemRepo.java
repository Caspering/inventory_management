package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.domain.ProcessedFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<FruitAndVege, ProcessedFood> {
}
