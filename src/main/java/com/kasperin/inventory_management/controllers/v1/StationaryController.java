package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.services.ResourceNotFoundException;
import com.kasperin.inventory_management.services.StationaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Api(description = "Stationary items API")
@RestController
@RequestMapping(StationaryController.BASE_URL)
@RequiredArgsConstructor
public class StationaryController {

    public static final String BASE_URL = "/api/v1/stationary";

    private final StationaryService stationaryService;


    @ApiOperation(value = "Get a list of all in stock stationary in the inventory",
            notes = "You can also query the inventory for a list of all stationary in inventory regardless of in stock amount")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Stationary> getAll(@RequestParam(value = "all", defaultValue = "") String all){
        if (all.equals("all")){ return stationaryService.findAll();
        }
        //just get what is in stock with qty >=1 if no query
        return stationaryService.findAllInStock();
    }

    @ApiOperation(value = "Get stationary by Id")
    @GetMapping({"/{id}"})
    //@ResponseStatus(HttpStatus.OK)
    public Optional<Stationary> getById(@PathVariable Long id) {
            return stationaryService.findById(id);
    }

    @ApiOperation(value = "Get stationary by name")
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Stationary getByName(@PathVariable("name") String name){
        return stationaryService.findByName(name);
    }

    @ApiOperation(value = "Update a stationary property by id", notes = "When zero (0) is assigned to an inStockQuantity field, the stationary item will be deleted")
    @PatchMapping({"/{id}"})
    public Optional<Stationary> updateById(@PathVariable Long id, @RequestBody Stationary stationary){
        return stationaryService.updateById(id,stationary);
    }

    @ApiOperation(value = "Add new stationary to the inventory", notes = "A new stationary MUST have a name, and quantity greater or equal to 1")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Stationary createNewStationary(@RequestBody Stationary stationary) {
        return stationaryService.save(stationary);
    }

    @ApiOperation(value = "Delete a stationary by its id")
    @DeleteMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        stationaryService.deleteById(id);
    }

}
