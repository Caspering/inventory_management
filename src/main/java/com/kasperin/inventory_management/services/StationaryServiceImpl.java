package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.controllers.v1.StationaryController;
import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.repository.StationaryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
public class StationaryServiceImpl implements StationaryService{

    private final StationaryRepository stationaryRepository;


    /*private String getStationaryUrl(Long id) {
        return StationaryController.BASE_URL + "/id/" + id;
    }*/

    @Override
    public Stationary save(Stationary stationary) {

        Stationary savedSt = stationaryRepository.save(stationary);

        //savedSt.setStationaryUrl(getStationaryUrl(savedSt.getId()));

        log.info("Stationary item: " + stationary.getName() + ", has been saved");

        return savedSt;
    }

    @Override
    public Optional<Stationary> updateById(Long id, Stationary stationaryBody) {

        Optional<Stationary> stationaryInRepo = stationaryRepository.findById(id);

        /*If the stationary entity with the requested id exists in the Repo update its
        field or else delete it if the inStockQuantity state/field is set to zero*/
        if (stationaryInRepo.isPresent()) {
            return stationaryInRepo.map(stationary -> {
                if (isRealEntity(stationaryBody, stationary))
                    return stationaryRepository.save(stationary);

                    /*if the inStockQuantity is set to zero delete the entity
                    from db and return null*/
                    stationaryRepository.delete(stationary);
                return null;
            });
        }

        /*But if the stationary with the requested id is not found*/
        else{
            log.debug("Object not found");
            throw new RuntimeException("Object not found");
        }
    }


    /*if an entity has an inStockQuantity greater than 1 isRealEntity
    will update its fields and return true*/
    private boolean isRealEntity(Stationary newStationary, Stationary stationaryInDb) {
        /*if the inStockQuantity is not updated to zero
        then update the entity with their fields.*/
        if (newStationary.getInStockQuantity() >= 1) {
            stationaryInDb.setInStockQuantity(newStationary.getInStockQuantity());
            if (newStationary.getName() != null) {
            stationaryInDb.setName(newStationary.getName());
            }
            if (newStationary.getBarcode() != null) {
            stationaryInDb.setBarcode(newStationary.getBarcode());
            }
            if (newStationary.getPrice() != null) {
            stationaryInDb.setPrice(newStationary.getPrice());
            }
            return true;
        }
        return false;
    }


    @Override
    public List<Stationary> findAll() {
        return stationaryRepository.findAll()
                .stream()
                .map(s -> {
                    if (s.getInStockQuantity() == 0) {
                        stationaryRepository.delete(s);
                    }
                    return s;
                })
                //.filter(s -> s.getInStockQuantity() != 0)
                .collect(Collectors.toList());
    }

    @Override
    public Stationary findByName(String name) {
//        Stationary st =
//      st.setStationaryUrl(getStationaryUrl(st.getId()));
        return stationaryRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Optional<Stationary> findById(Long id) {
        return stationaryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
            stationaryRepository.deleteById(id);
    }
}





//        List<Stationary> st = stationaryRepository.findAll();
//        st.removeIf(s -> s.getInStockQuantity() == 0);