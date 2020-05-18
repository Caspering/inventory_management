package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.Stationary;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
public interface StationaryService {

    List<Stationary> findAll();

    Stationary findByName(String name);

    Optional<Stationary> findById(Long id);

    Stationary save(@Valid Stationary stationary);

    Optional<Stationary> updateById(Long id, @Valid Stationary stationary);

    void deleteById(Long id);


}
