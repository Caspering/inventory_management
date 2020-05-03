package com.kasperin.inventory_management.controllers.v1;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProcessedFood findById(@PathVariable Long id){
        return processedFoodService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProcessedFood createNewProcessedFood(@RequestBody ProcessedFood processedFood){
        return processedFoodService.save(processedFood);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        processedFoodService.deleteById(id);
    }

}
