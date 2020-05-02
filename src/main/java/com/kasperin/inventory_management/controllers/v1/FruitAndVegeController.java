package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.services.FruitAndVegeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FruitAndVegeController.BASE_URL)
public class FruitAndVegeController {

    public static final String BASE_URL = "/api/v1/customers";

    private final FruitAndVegeService fruitAndVegeService;

    public FruitAndVegeController(FruitAndVegeService fruitAndVegeService) {
        this.fruitAndVegeService = fruitAndVegeService;
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public FruitAndVege getAllFruitAndVegeList(){
//        return fruitAndVegeService.findAll();
//    }




}
