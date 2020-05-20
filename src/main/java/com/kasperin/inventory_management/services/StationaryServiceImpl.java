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
    public Optional<Stationary> updateById(Long id, @Valid Stationary newStationary) {
        return getStationaryById(id)
                .map(oldStationary -> {
                    if (newStationary.getInStockQuantity() == null)
                        newStationary.setInStockQuantity(oldStationary.getInStockQuantity());

                    if(newStationary.getInStockQuantity() >= 1) {
                        oldStationary.setInStockQuantity(newStationary.getInStockQuantity());

                        if (newStationary.getName() != null)
                            oldStationary.setName(newStationary.getName());

                        if (newStationary.getBarcode() != null)
                            oldStationary.setBarcode(newStationary.getBarcode());

                        if (newStationary.getPrice() != null)
                            oldStationary.setPrice(newStationary.getPrice());

                        return stationaryRepository.save(oldStationary);

                    }else if (newStationary.getInStockQuantity() == 0)
                        stationaryRepository.delete(oldStationary);
                        return null;

        });

    }

    @Override
    public List<Stationary> findAll() {
        return stationaryRepository.findAll();
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

