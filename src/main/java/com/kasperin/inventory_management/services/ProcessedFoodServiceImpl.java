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
import java.util.stream.Collectors;

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
    public Optional<ProcessedFood> updateById(Long id, @Valid ProcessedFood processedFoodPatch) {
            return getProcessedFood(id)
                .map(processedFoodInDB -> {
                    if (processedFoodPatch.getInStockQuantity() == null)
                        processedFoodPatch.setInStockQuantity(processedFoodInDB.getInStockQuantity());

                    if(processedFoodPatch.getInStockQuantity() >= 0) {
                        processedFoodInDB.setInStockQuantity(processedFoodPatch.getInStockQuantity());

                        if (processedFoodPatch.getName() != null)
                            processedFoodInDB.setName(processedFoodPatch.getName());

                        if (processedFoodPatch.getBarcode() != null)
                            processedFoodInDB.setBarcode(processedFoodPatch.getBarcode());

                        if (processedFoodPatch.getPrice() != null)
                            processedFoodInDB.setPrice(processedFoodPatch.getPrice());

                        if (processedFoodPatch.getFoodType() != null)
                            processedFoodInDB.setFoodType(processedFoodPatch.getFoodType());

                        if (processedFoodPatch.getMfgDate() != null)
                            processedFoodInDB.setMfgDate(processedFoodPatch.getMfgDate());

                        if (processedFoodPatch.getExpDate() != null)
                            processedFoodInDB.setExpDate(processedFoodPatch.getExpDate());

                        log.info("The stationary item : " + processedFoodInDB.getName() + " was updated");
                        return processedFoodRepo.save(processedFoodInDB);
                    }
                    return processedFoodInDB;
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
    public List<ProcessedFood> findAllInStock() {
        return processedFoodRepo.findAll()
                .stream()
                .filter(processedFood -> processedFood.getInStockQuantity()>=1)
                .collect(Collectors.toList());
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
