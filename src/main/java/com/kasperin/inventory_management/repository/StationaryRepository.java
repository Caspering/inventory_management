package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.domain.Stationary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationaryRepository extends JpaRepository<Stationary, Long> {

    Stationary findByNameIgnoreCase(String name);

}
