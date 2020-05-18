package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.repository.StationaryRepository;
import com.kasperin.inventory_management.validator_services.OnCreate;
import com.kasperin.inventory_management.validator_services.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class StationaryServiceImpl implements StationaryService{

    private final StationaryRepository stationaryRepository;

    @Override
    @Validated(OnCreate.class)
    public Stationary save(@Valid Stationary stationary) {

        Stationary savedSt = stationaryRepository.save(stationary);

        log.info("Stationary item: " + stationary.getName() + ", has been saved");

        return savedSt;
    }

    @Override
    @Validated(OnUpdate.class)
    public Optional<Stationary> updateById(Long id, @Valid Stationary stationaryBody) {

        /*If the stationary entity with the requested id exists in the Repo, update its
        field or else delete it if the inStockQuantity state/field is set to zero*/
        if (stationaryRepository.existsById(id)) {
        Optional<Stationary> stationaryInRepo = stationaryRepository.findById(id);
            return stationaryInRepo.map(stationary -> {

                /*Check if inStockQuantity is greater than 1 and Apply changes*/
                if (isRealEntity(stationaryBody, stationary)) {
                    return stationaryRepository.save(stationary);
                }
                    /*if the inStockQuantity is set to zero delete the entity
                    from db and return null*/
                stationaryRepository.delete(stationary);
                return null;
            });
        }

        /*But if the stationary with the requested id is not found*/
        else{
            throw new ResourceNotFoundException
            ("Stationary object with the requested id was not found");
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

