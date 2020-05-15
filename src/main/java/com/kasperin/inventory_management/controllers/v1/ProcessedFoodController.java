package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.services.ProcessedFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ProcessedFoodController.BASE_URL)
@RequiredArgsConstructor
public class ProcessedFoodController {

    public static final String BASE_URL = "/api/v1/processedFoods";

    private final ProcessedFoodService processedFoodService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessedFood> getAll(@RequestParam("type") Optional<FoodType> foodType) {
        if (foodType.isPresent()) {
            return processedFoodService.findByType(foodType.get());
        } else { //else return everything
            return processedFoodService.findAll();
        }
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProcessedFood getByName(@PathVariable String name) {
        return processedFoodService.findByName(name);
    }

    @GetMapping({"/{ID}"})
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProcessedFood> getById(@PathVariable String ID) {
        return processedFoodService.findById(Long.valueOf(ID));
    }

    @PatchMapping({"/{id}"})
    public Optional<ProcessedFood> updateById(@PathVariable Long id, @RequestBody ProcessedFood processedFood){
        return processedFoodService.updateById(id,processedFood);
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
