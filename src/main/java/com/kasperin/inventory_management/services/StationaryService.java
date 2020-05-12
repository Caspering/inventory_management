package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.Stationary;

import java.util.List;
import java.util.Optional;

public interface StationaryService {

    List<Stationary> findAll();

    Stationary findByName(String name);

    Optional<Stationary> findById(Long id);

    Stationary save(Stationary stationary);

    void deleteById(Long id);


}
