package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.repository.StationaryRepository;
import com.kasperin.inventory_management.validator_services.OnCreate;
import com.kasperin.inventory_management.validator_services.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.management.BadAttributeValueExpException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class StationaryServiceImpl implements StationaryService{

    private final StationaryRepository stationaryRepository;
    private Optional<Stationary> getStationaryById(Long id) {
        if (stationaryRepository.existsById(id)) {
            return stationaryRepository.findById(id);
        }else{
            throw new ResourceNotFoundException
                    ("Stationary object with the requested id: "+ id +" was not found");
        }
    }
    private Stationary getStationaryByNameIgnoreCase(String name) {
        if (stationaryRepository.existsByNameIgnoreCase(name)) {
            return stationaryRepository.findByNameIgnoreCase(name);
        }else{
            throw new ResourceNotFoundException("The Stationary item with name: "
                    + name + " does not exist");
        }
    }

    @Override
    @Validated(OnCreate.class)
    public Stationary save(@Valid Stationary stationary) {

        if (!(stationaryRepository.existsByBarcode(stationary.getBarcode()))) {

            Stationary savedSt = stationaryRepository.save(stationary);

            log.info("Stationary item: " + stationary.getName() + ", has been saved");

            return savedSt;
        }else
            log.error("A stationary item with barcode: " + stationary.getBarcode() +" exists");
        return null;
    }

    @Override
    @Validated(OnUpdate.class)
    public Optional<Stationary> updateById(Long id, @Valid Stationary stationaryPatch) {
        return getStationaryById(id)
                .map(stationaryInDB -> {
                    if (stationaryPatch.getInStockQuantity() == null)
                        stationaryPatch.setInStockQuantity(stationaryInDB.getInStockQuantity());

                    if(stationaryPatch.getInStockQuantity() >= 0) {
                        stationaryInDB.setInStockQuantity(stationaryPatch.getInStockQuantity());

                        if (stationaryPatch.getName() != null)
                            stationaryInDB.setName(stationaryPatch.getName());

                        if (stationaryPatch.getBarcode() != null)
                            stationaryInDB.setBarcode(stationaryPatch.getBarcode());

                        if (stationaryPatch.getPrice() != null)
                            stationaryInDB.setPrice(stationaryPatch.getPrice());

                        return stationaryRepository.save(stationaryInDB);

                    }
                    return stationaryInDB;

        });

    }

    @Override
    public List<Stationary> findAll() {
        return stationaryRepository.findAll();
    }

    @Override
    public List<Stationary> findAllInStock() {

        return stationaryRepository.findAll()
                .stream()
                .filter(stationary -> stationary.getInStockQuantity()>=1)
//                .map(Stationary::new)
                .collect(Collectors.toList());

        /*List<Stationary> allStationary = stationaryRepository.findAll();
        for (Stationary stationary : allStationary){
            List<instock>
            if (stationary.getInStockQuantity() != 0)
                stationaryRepository.delete(oldStationary);
        }
        return null;*/
    }

    @Override
    public Stationary findByName(String name) {
        return getStationaryByNameIgnoreCase(name);
    }

    @Override
    public Optional<Stationary> findById(Long id) {
        return getStationaryById(id);
    }

    @Override
    public void deleteById(Long id) {
        getStationaryById(id).map(stationary -> {stationaryRepository.delete(stationary);
            return null;
        });
    }
}

