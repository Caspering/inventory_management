package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.controllers.v1.FruitAndVegeController;
import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProcessedFoodServiceImpl implements ProcessedFoodService {

    private final ProcessedFoodRepo processedFoodRepo;


    public ProcessedFoodServiceImpl(ProcessedFoodRepo processedFoodRepo) {
        this.processedFoodRepo = processedFoodRepo;
    }

    private String getProcessedFoodUrl(Long id) {
        return FruitAndVegeController.BASE_URL + "/" + id;
    }

    @Override
    public ProcessedFood save(ProcessedFood processedFood) {
        return processedFoodRepo.save(processedFood);
    }

    @Override
    public Optional<ProcessedFood> findById(Long id) {
        return processedFoodRepo.findById(id);
    }

    @Override
    public Optional<ProcessedFood> findByName(String name) {
        return processedFoodRepo.findByName(name);
    }

    @Override
    public List<ProcessedFood> findAll() {
        return processedFoodRepo.findAll();
    }

    @Override
    public List<ProcessedFood> findByType(FoodType foodType) {
        return processedFoodRepo.findAllByFoodType(foodType);
    }

    @Override
    public void deleteById(Long id) {
        processedFoodRepo.deleteById(id);
    }
}
