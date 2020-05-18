package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.ProcessedFood;
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
    private Optional<Stationary> getStationaryById(Long id) {
        if (stationaryRepository.existsById(id)) {
            return stationaryRepository.findById(id);
        }else{
            throw new ResourceNotFoundException
                    ("Stationary object with the requested id: "+ id +" was not found");
        }
    }

    @Override
    @Validated(OnCreate.class)
    public Stationary save(@Valid Stationary stationary) {

        Stationary savedSt = stationaryRepository.save(stationary);

        log.info("Stationary item: " + stationary.getName() + ", has been saved");

        return savedSt;
    }

    @Override
    @Validated(OnUpdate.class)
    public Optional<Stationary> updateById(Long id, @Valid Stationary newData) {
        return getStationaryById(id)
                .map(stationary -> {
                    if(newData.getInStockQuantity() >= 1) {
                        stationary.setInStockQuantity(newData.getInStockQuantity());

                        if (newData.getName() != null)
                            stationary.setName(newData.getName());

                        if (newData.getBarcode() != null)
                            stationary.setBarcode(newData.getBarcode());

                        if (newData.getPrice() != null)
                            stationary.setPrice(newData.getPrice());

                        return stationaryRepository.save(stationary);
                    }
                stationaryRepository.delete(stationary);
                return null;
        });
    }

    @Override
    public List<Stationary> findAll() {
        return stationaryRepository.findAll();
    }

    @Override
    public Stationary findByName(String name) {
        return stationaryRepository.findByNameIgnoreCase(name);
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

