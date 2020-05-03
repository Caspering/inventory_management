package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.services.FruitAndVegeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(FruitAndVegeController.BASE_URL)
public class FruitAndVegeController {

    public static final String BASE_URL = "/api/v1/customers";

    private final FruitAndVegeService fruitAndVegeService;

    public FruitAndVegeController(FruitAndVegeService fruitAndVegeService) {
        this.fruitAndVegeService = fruitAndVegeService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FruitAndVege> getAllFruitAndVegeList(){
        return fruitAndVegeService.findAll();
    }

    @GetMapping("{name}")
    public ResponseEntity<FruitAndVegeDTO> findByName( @PathVariable String name){
        return new ResponseEntity<FruitAndVegeDTO>(
                fruitAndVegeService.findByName(name), HttpStatus.OK
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FruitAndVegeDTO createNewVendor(@RequestBody FruitAndVegeDTO fruitAndVegeDTO){
        return fruitAndVegeService.createNewFruitAndVege(fruitAndVegeDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteFruitAndVege(@PathVariable Long id){
        fruitAndVegeService.deleteById(id);
    }

}
