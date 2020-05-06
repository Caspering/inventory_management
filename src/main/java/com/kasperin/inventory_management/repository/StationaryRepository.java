package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.domain.Stationary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationaryRepository extends JpaRepository<Stationary, Long> {

    Optional<Stationary> findByName(String name);

}