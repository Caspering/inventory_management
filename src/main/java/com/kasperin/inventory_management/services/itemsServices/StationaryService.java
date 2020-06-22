package com.kasperin.inventory_management.services.itemsServices;

import com.kasperin.inventory_management.domain.Items.Stationary;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
public interface StationaryService {

    List<Stationary> findAll();

    List<Stationary> findAllByName(String name);

    List<Stationary> findAllByNameContaining(String name);

    List<Stationary> findAllInStock();

    Stationary findByName(String name);

    Optional<Stationary> findById(Long id);

    Stationary save(@Valid Stationary stationary) throws Exception;

    Optional<Stationary> updateById(Long id, @Valid Stationary newStationary);

    // findByBarcodeOrNameIgnoreCase

    void deleteById(Long id);

    boolean existsById(Stationary stationary);
}
