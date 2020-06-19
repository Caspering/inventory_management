package com.kasperin.inventory_management.repository.ItemsRepository;

import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.domain.Items.Stationary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FruitAndVegeRepository extends JpaRepository<FruitAndVege, Long> {

    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameContainingIgnoreCase(String name);
    boolean existsByBarcode(String barcode);
    boolean existsByBarcodeOrNameIgnoreCase(String barcode, String name);

    FruitAndVege findByBarcodeOrNameIgnoreCase(String barcode, String name);
    FruitAndVege findByNameIgnoreCase(String name);
    FruitAndVege findByBarcode(String barcode);

    List<FruitAndVege> findAllByNameIgnoreCase(String name);
    List<FruitAndVege> findAllByNameContainingIgnoreCase(String name);



}
