package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import com.kasperin.inventory_management.validator_services.OnCreate;
import com.kasperin.inventory_management.validator_services.OnUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProcessedFoodServiceImpl implements ProcessedFoodService {

    private final ProcessedFoodRepo processedFoodRepo;
    private Optional<ProcessedFood> getProcessedFood(Long id) {
        if (processedFoodRepo.existsById(id)) {
            return processedFoodRepo.findById(id);
        }else{
            throw new ResourceNotFoundException
                    ("The Processed food object with the requested id: "+ id +" was not found");
        }
    }
    private ProcessedFood getStationaryByNameIgnoreCase(String name) {
        if (processedFoodRepo.existsByNameIgnoreCase(name)) {
            return processedFoodRepo.findByNameIgnoreCase(name);
        }else{
            throw new ResourceNotFoundException("The Processes food item with name: "
                    + name + " does not exist");
        }
    }


    public ProcessedFoodServiceImpl(ProcessedFoodRepo processedFoodRepo) {
        this.processedFoodRepo = processedFoodRepo;
    }

    @Override
    @Validated(OnCreate.class)
    public ProcessedFood save(@Valid ProcessedFood processedFood) {
        return processedFoodRepo.save(processedFood);
    }

    @Override
    @Validated(OnUpdate.class)
    public Optional<ProcessedFood> updateById(Long id, @Valid ProcessedFood newProcessedFoodData) {
            return getProcessedFood(id)
                .map(oldProcessedFoodData -> {
                    if (newProcessedFoodData.getInStockQuantity() == null)
                        newProcessedFoodData.setInStockQuantity(oldProcessedFoodData.getInStockQuantity());

                    if(newProcessedFoodData.getInStockQuantity() >= 1) {
                        oldProcessedFoodData.setInStockQuantity(newProcessedFoodData.getInStockQuantity());

                        if (newProcessedFoodData.getName() != null)
                            oldProcessedFoodData.setName(newProcessedFoodData.getName());

                        if (newProcessedFoodData.getBarcode() != null)
                            oldProcessedFoodData.setBarcode(newProcessedFoodData.getBarcode());

                        if (newProcessedFoodData.getPrice() != null)
                            oldProcessedFoodData.setPrice(newProcessedFoodData.getPrice());

                        if (newProcessedFoodData.getFoodType() != null)
                            oldProcessedFoodData.setFoodType(newProcessedFoodData.getFoodType());

                        if (newProcessedFoodData.getMfgDate() != null)
                            oldProcessedFoodData.setMfgDate(newProcessedFoodData.getMfgDate());

                        if (newProcessedFoodData.getExpDate() != null)
                            oldProcessedFoodData.setExpDate(newProcessedFoodData.getExpDate());

                        return processedFoodRepo.save(oldProcessedFoodData);
                        
                    }else if (newProcessedFoodData.getInStockQuantity() == 0)
                                processedFoodRepo.delete(oldProcessedFoodData);
                    return null;
            });
    }

    @Override
    public Optional<ProcessedFood> findById(Long id) {
        return getProcessedFood(id);
    }

    @Override
    public ProcessedFood  findByName(String name) {
        return getStationaryByNameIgnoreCase(name);
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
        getProcessedFood(id).map(processedFood -> {processedFoodRepo.delete(processedFood);
            return null;
        });
    }
}
