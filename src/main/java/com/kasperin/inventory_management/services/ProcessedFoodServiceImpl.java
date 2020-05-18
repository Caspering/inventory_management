package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
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
                    ("Stationary object with the requested id: "+ id +" was not found");
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
    public Optional<ProcessedFood> updateById(Long id, @Valid ProcessedFood newData) {
            return getProcessedFood(id)
                .map(processedFood -> {
                    if(newData.getInStockQuantity() >= 1) {
                        processedFood.setInStockQuantity(newData.getInStockQuantity());

                        if (newData.getName() != null)
                        processedFood.setName(newData.getName());

                        if (newData.getBarcode() != null)
                        processedFood.setBarcode(newData.getBarcode());

                        if (newData.getPrice() != null)
                        processedFood.setPrice(newData.getPrice());

                        return processedFoodRepo.save(processedFood);
                    }
                processedFoodRepo.delete(processedFood);
                return null;
            });
    }

    @Override
    public Optional<ProcessedFood> findById(Long id) {
        return getProcessedFood(id);
    }

    @Override
    public ProcessedFood  findByName(String name) {
        return processedFoodRepo.findByNameIgnoreCase(name);
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
