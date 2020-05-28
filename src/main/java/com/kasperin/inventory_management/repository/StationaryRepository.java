package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.domain.Stationary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationaryRepository extends JpaRepository<Stationary, Long> {

    Stationary findByNameIgnoreCase(String name);
    boolean existsById(Long id);
    boolean existsByNameIgnoreCase(String name);
    List<Stationary> findAllByNameIgnoreCase(String name);
    boolean existsByBarcode(String barcode);
    Stationary findByBarcode(String barcode);

}
