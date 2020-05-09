package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.services.StationaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(StationaryController.BASE_URL)
@RequiredArgsConstructor
public class StationaryController {

    public static final String BASE_URL = "/api/v1/stationary/";

    private final StationaryService stationaryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Stationary> getAllStationary(){
        return stationaryService.findAll();
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


    //works also
    @GetMapping({"{ID}"})
    @ResponseStatus(HttpStatus.OK)
    public Optional<Stationary> getById(@PathVariable String ID) {
        return stationaryService.findById(Long.valueOf(ID));
    }

//        //Does not work
//    @GetMapping("{name}")
//    public ResponseEntity<Stationary> getByName(@QueryParam("name") String name){
//        return new ResponseEntity<Stationary>(
//                stationaryService.findByName(name), HttpStatus.OK
//        );
//    }

//    //works but conflicts with get by ID
//    @GetMapping("name")
//    public Optional<Stationary> getByName(@QueryParam("name") String name){
//        return stationaryService.findByName(name);
//    }




//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public Optional<Stationary> getAll(@RequestParam("ID") String ID) {
//
//        return stationaryService.findById(Long.valueOf(ID));
//
//    }
//
//    @GetMapping(value = "/by-name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public Stationary getByName(@PathVariable String name) {
//        return stationaryService.findByName(name).orElseThrow(ResourceNotFoundException::new);
//    }



// //This code works
//    @GetMapping({"{id}"})
//    @ResponseStatus(HttpStatus.OK)
//    public Optional<Stationary> getById(@PathVariable Long id) {
//        return stationaryService.findById(id);
//    }

//    //works also
//    @GetMapping({"{ID}"})
//    @ResponseStatus(HttpStatus.OK)
//    public Optional<Stationary> getById(@PathVariable String ID) {
//        return stationaryService.findById(Long.valueOf(ID));
//    }
//
//      //works but conflicts with get by ID
//    @GetMapping("{name}")
//    public Optional<Stationary> getByName(@PathVariable("name") String name){
//    return stationaryService.findByName(name);
//    }





}
