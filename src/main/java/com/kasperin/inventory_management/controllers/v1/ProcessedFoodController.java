package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.services.ProcessedFoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(description = "Processed foods API")
@RestController
@RequestMapping(ProcessedFoodController.BASE_URL)
@RequiredArgsConstructor
public class ProcessedFoodController {

    public static final String BASE_URL = "/api/v1/processedFoods";

    private final ProcessedFoodService processedFoodService;

    @ApiOperation(value = "View List of all processed foods in inventory",
            notes = "You can also query the inventory for a list of processed foods by food type ie. VEGAN and NONVEGAN. Or for a list of all in inventory" )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessedFood> getAll(@RequestParam(value = "type", defaultValue = "") Optional<FoodType> foodType,
                                      @RequestParam(value = "all", defaultValue = "") String all) {
        if (foodType.isPresent()) {
            return processedFoodService.findByType(foodType.get());
        } else if (all.equals("all")){
            return processedFoodService.findAll();
        } //else return everything
            return processedFoodService.findAllInStock();
    }

    @ApiOperation(value = "Get a processed food in inventory by name")
    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProcessedFood getByName(@PathVariable String name) {
        return processedFoodService.findByName(name);
    }

    @ApiOperation(value = "Get a processed food in inventory by id")
    @GetMapping({"/{ID}"})
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProcessedFood> getById(@PathVariable String ID) {
        return processedFoodService.findById(Long.valueOf(ID));
    }

    @ApiOperation(value = "Update a processed food property by id", notes = "When zero (0) is assigned to an inStockQuantity field, the processed food will be deleted")
    @PatchMapping({"/{id}"})
    public Optional<ProcessedFood> updateById(@PathVariable Long id, @RequestBody ProcessedFood processedFood){
        return processedFoodService.updateById(id,processedFood);
    }

    @ApiOperation(value = "Add new processed food to the inventory", notes = "A new processed food MUST have a name and quantity greater or equal to 1")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProcessedFood createNewProcessedFood(@RequestBody ProcessedFood processedFood) {
        return processedFoodService.save(processedFood);
    }

    @ApiOperation(value = "Delete a processed food by its id")
    @DeleteMapping({"{ID}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String ID) {
        processedFoodService.deleteById(Long.valueOf(ID));
    }
}
