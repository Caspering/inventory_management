package com.kasperin.inventory_management.repository.ItemsRepository;

import com.kasperin.inventory_management.domain.Items.Stationary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationaryRepository extends JpaRepository<Stationary, Long> {

    boolean existsById(Long id);
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameContainingIgnoreCase(String name);
    boolean existsByBarcode(String barcode);
    boolean existsByBarcodeOrNameIgnoreCase(String barcode, String name);

    Stationary findByBarcode(String barcode);
    Stationary findByNameIgnoreCase(String name);
    Stationary findByBarcodeOrNameIgnoreCase(String barcode, String name);


    List<Stationary> findAllByNameContainingIgnoreCase(String name);
    List<Stationary> findAllByNameIgnoreCase(String name);
}
