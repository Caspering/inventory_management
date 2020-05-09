package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.services.StationaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(StationaryController.BASE_URL)
@RequiredArgsConstructor
public class StationaryController {

    public static final String BASE_URL = "/api/v1/stationary";

    private final StationaryService stationaryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Stationary> getAllStationary(){
        return stationaryService.findAll();
    }

    @GetMapping({"/id/{ID}"})
    @ResponseStatus(HttpStatus.OK)
    public Optional<Stationary> getById(@PathVariable String ID) {
        return stationaryService.findById(Long.valueOf(ID));
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Stationary getByName(@PathVariable("name") String name){
        return stationaryService.findByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Stationary createNewStationary(@RequestBody Stationary stationary) {
        return stationaryService.save(stationary);
    }

    @DeleteMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        stationaryService.deleteById(id);
    }

}
