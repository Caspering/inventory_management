package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import com.kasperin.inventory_management.services.ProcessedFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ProcessedFoodController.BASE_URL)
@RequiredArgsConstructor
public class ProcessedFoodController {

    public static final String BASE_URL = "api/v1/processedFoods/";

    private final ProcessedFoodService processedFoodService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessedFood> findAll(@RequestParam("type") Optional<FoodType> foodType) {
        if (foodType.isPresent()) { // type specified, filter
            return processedFoodService.findByType(foodType.get());
        } else { // nope, return everything
            return processedFoodService.findAll();
        }
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessedFood findByName(@PathVariable String name) {
        return processedFoodService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("{ID}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessedFood findById(@PathVariable String ID) {
        return processedFoodService.findById(Long.valueOf(ID));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProcessedFood createNewProcessedFood(@RequestBody ProcessedFood processedFood) {
        return processedFoodService.save(processedFood);
    }

    @DeleteMapping({"{ID}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String ID) {
        processedFoodService.deleteById(Long.valueOf(ID));
    }
}
