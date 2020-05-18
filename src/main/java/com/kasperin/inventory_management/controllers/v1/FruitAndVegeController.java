package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.services.FruitAndVegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(FruitAndVegeController.BASE_URL)
@RequiredArgsConstructor
public class FruitAndVegeController {

    public static final String BASE_URL = "/api/v1/fruitAndVeges";

    private final FruitAndVegeService fruitAndVegeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FruitAndVege> getAllFruitAndVege(){
        return fruitAndVegeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FruitAndVegeDTO> getById( @PathVariable String id){
        return new ResponseEntity<FruitAndVegeDTO>(
                fruitAndVegeService.findById(Long.valueOf(id)), HttpStatus.OK
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<FruitAndVegeDTO> getByName( @PathVariable String name){
        return new ResponseEntity<FruitAndVegeDTO>(
                fruitAndVegeService.findByName(name), HttpStatus.OK
        );
    }

    @PatchMapping({"/{id}"})
    public Optional<FruitAndVege> updateById(@PathVariable Long id, @RequestBody FruitAndVege fav){
        return fruitAndVegeService.updateById(id,fav);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FruitAndVegeDTO createNewFruitAndVege(@RequestBody FruitAndVegeDTO fruitAndVegeDTO){
        return fruitAndVegeService.createNewFruitAndVege(fruitAndVegeDTO);
    }

    @DeleteMapping({"{ID}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteFruitAndVege(@PathVariable String ID){
        fruitAndVegeService.deleteById(Long.valueOf(ID));
    }

}
