package com.kasperin.inventory_management.repository.ItemsRepository;

import com.kasperin.inventory_management.domain.Items.ProcessedFood;
import com.kasperin.inventory_management.domain.Items.Stationary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationaryRepository extends JpaRepository<Stationary, Long> {

    Stationary findByNameIgnoreCase(String name);
    boolean existsById(Long id);
    boolean existsByNameIgnoreCase(String name);
    List<Stationary> findAllByNameIgnoreCase(String name);
    boolean existsByBarcode(String barcode);
    Stationary findByBarcode(String barcode);
    boolean existsByBarcodeOrNameIgnoreCase(String barcode, String name);
    Stationary findByBarcodeOrNameIgnoreCase(String barcode, String name);

}
