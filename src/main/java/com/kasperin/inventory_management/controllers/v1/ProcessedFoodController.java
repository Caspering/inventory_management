package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.services.ProcessedFoodService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProcessedFoodController.BASE_URL)
public class ProcessedFoodController {

    public static final String BASE_URL = "api/v1/processedFoods/";

    private final ProcessedFoodService processedFoodService;

    public ProcessedFoodController(ProcessedFoodService processedFoodService) {
        this.processedFoodService = processedFoodService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessedFood> findAll(){
        return processedFoodService.findAll();
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessedFood findByName(@PathVariable String name){
        return processedFoodService.findByName(name);
    }

    @GetMapping("/NONVEGAN")
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessedFood> findAllNonVegan() {
        return processedFoodService.findAllNonVegan();
    }

    @GetMapping("/VEGAN")
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessedFood> findAllVegan() {
        return processedFoodService.findAllVegan();
    }

    @GetMapping("{ID}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessedFood findById(@PathVariable String ID){
        return processedFoodService.findById(Long.valueOf(ID));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProcessedFood createNewProcessedFood(@RequestBody ProcessedFood processedFood){
        return processedFoodService.save(processedFood);
    }

    @DeleteMapping({"{ID}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String ID){
        processedFoodService.deleteById(Long.valueOf(ID));
    }

}
