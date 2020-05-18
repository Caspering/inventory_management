package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.services.FruitAndVegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(description = "Fruit and Vegetables API")
@RestController
@RequestMapping(FruitAndVegeController.BASE_URL)
@RequiredArgsConstructor
public class FruitAndVegeController {

    public static final String BASE_URL = "/api/v1/fruitAndVeges";

    private final FruitAndVegeService fruitAndVegeService;

    @ApiOperation(value = "Get a list of all fruits and vegetables in the inventory.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FruitAndVege> getAllFruitAndVege(){
        return fruitAndVegeService.findAll();
    }

    @ApiOperation(value = "Get fruit or vegetable by Id")
    @GetMapping("/{id}")
    public ResponseEntity<FruitAndVegeDTO> getById( @PathVariable String id){
        return new ResponseEntity<FruitAndVegeDTO>(
                fruitAndVegeService.findById(Long.valueOf(id)), HttpStatus.OK
        );
    }

    @ApiOperation(value = "Get fruit or vegetable by name")
    @GetMapping("/name/{name}")
    public ResponseEntity<FruitAndVegeDTO> getByName( @PathVariable String name){
        return new ResponseEntity<FruitAndVegeDTO>(
                fruitAndVegeService.findByName(name), HttpStatus.OK
        );
    }

    @ApiOperation(value = "Update a fruit or vegetable property by id", notes = "When zero (0) is assigned to an inStockQuantity field, the fruit or vegetable will be deleted")
    @PatchMapping({"/{id}"})
    public Optional<FruitAndVege> updateById(@PathVariable Long id, @RequestBody FruitAndVege fav){
        return fruitAndVegeService.updateById(id,fav);
    }

    @ApiOperation(value = "Add new vegetable to the inventory", notes = "A new fruit and vegetable MUST have a name and quantity greater or equal to 1")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FruitAndVegeDTO createNewFruitAndVege(@RequestBody FruitAndVegeDTO fruitAndVegeDTO){
        return fruitAndVegeService.createNewFruitAndVege(fruitAndVegeDTO);
    }

    @ApiOperation(value = "Delete a fruit or vegetable by its id")
    @DeleteMapping({"{ID}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteFruitAndVege(@PathVariable String ID){
        fruitAndVegeService.deleteById(Long.valueOf(ID));
    }

}
