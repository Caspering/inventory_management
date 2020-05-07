package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.services.ResourceNotFoundException;
import com.kasperin.inventory_management.services.StationaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Stationary getByName(@PathVariable String name) {
        return stationaryService.findByName(name).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("{ID}")
    @ResponseStatus(HttpStatus.OK)
    public Stationary getById(@PathVariable String ID) {
        return stationaryService.findById(Long.valueOf(ID)).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Stationary createNewStationary(@RequestBody Stationary stationary) {
        return stationaryService.save(stationary);
    }

    @DeleteMapping({"{ID}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String ID) {
        stationaryService.deleteById(Long.valueOf(ID));
    }


}
