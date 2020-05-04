package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.controllers.v1.FruitAndVegeController;
import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public ProcessedFood findById(Long id) {
        return processedFoodRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ProcessedFood findByName(String name) {
        return processedFoodRepo.findByName(name);
    }

    @Override
    public List<ProcessedFood> findAllVegan() {
        return processedFoodRepo.findAll()
                .stream()
                .filter(processedFood -> processedFood.getFoodType().equals(FoodType.VEGAN))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProcessedFood> findAllNonVegan() {
        return processedFoodRepo.findAll()
                .stream()
                .filter(processedFood -> processedFood.getFoodType().equals(FoodType.NONVEGAN))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<ProcessedFood> findAllByFoodType(FoodType foodType) {
//        return processedFoodRepo.findAllByFoodType(foodType);
//    }


    @Override
    public List<ProcessedFood> findAll() {
        return processedFoodRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if(processedFoodRepo.findById(id).isPresent()) {
            processedFoodRepo.deleteById(id);
            log.info("Processed Food with id:" + id +  " successfully deleted");
        } else {
            log.debug("Processed Food with id: " + id + " not found");
            throw new ResourceNotFoundException("Processed Food id: " + id + " not found");
        }

    }
}
