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

//    @GetMapping
//    public ResponseEntity<FruitAndVegeDTO> findAll(){
//        return new ResponseEntity<FruitAndVegeDTO>
//                (new List<FruitAndVegeDTO>(fruitAndVegeService.findAll()), HttpStatus.OK);
//    }

//    @GetMapping
//    public ResponseEntity<FruitAndVegeListDTO> findAll(){
//        return new ResponseEntity<FruitAndVegeListDTO>
//                (new FruitAndVegeListDTO(fruitAndVegeService.findAll()), HttpStatus.OK);
//    }

    @GetMapping("{name}")
    public ResponseEntity<FruitAndVegeDTO> findByName( @PathVariable String name){
        return new ResponseEntity<FruitAndVegeDTO>(
                fruitAndVegeService.findByName(name), HttpStatus.OK
        );
    }

}
