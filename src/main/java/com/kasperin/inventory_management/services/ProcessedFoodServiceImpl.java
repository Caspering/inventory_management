package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessedFoodServiceImpl implements ProcessedFoodService {

    private final ProcessedFoodRepo processedFoodRepo;

    public ProcessedFoodServiceImpl(ProcessedFoodRepo processedFoodRepo) {
        this.processedFoodRepo = processedFoodRepo;
    }

    @Override
    public ProcessedFood createNewProcessedFood(ProcessedFood processedFood) {
        return processedFoodRepo.save(processedFood);
    }

    @Override
    public ProcessedFood findById(Long id) {
        return processedFoodRepo.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ProcessedFood findByName(String name) {
        return processedFoodRepo.findByName(name);
    }

    @Override
    public ProcessedFood findByFoodType(FoodType foodType) {
        return processedFoodRepo.findProcessedFoodByFoodType(foodType);
    }

    @Override
    public List<ProcessedFood> findAll() {
        return processedFoodRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        processedFoodRepo.deleteById(id);

    }
}
