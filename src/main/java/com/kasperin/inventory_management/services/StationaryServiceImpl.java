package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.Stationary;

import java.util.List;
import java.util.Optional;

public class StationaryServiceImpl implements StationaryService{
    @Override
    public List<Stationary> findAll() {
        return null;
    }

    @Override
    public Optional<Stationary> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Stationary> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Stationary save(Stationary stationary) {
        return null;
    }

    @Override
    public Stationary post(Long id, Stationary stationary) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
