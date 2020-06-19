package com.kasperin.inventory_management.services.itemsServices;

import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.domain.Items.Stationary;
import com.kasperin.inventory_management.repository.ItemsRepository.StationaryRepository;
import com.kasperin.inventory_management.services.ResourceNotFoundException;
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
    private Stationary getStationaryByNameIgnoreCase(String name) {
        if (stationaryRepository.existsByNameIgnoreCase(name)) {
            return stationaryRepository.findByNameIgnoreCase(name);
        }else{
            throw new ResourceNotFoundException("The Stationary item with name: "
                    + name + " does not exist");
        }
    }
    private List<Stationary> getAllStationaryByNameIgnoreCase(String name) {
        if (stationaryRepository.existsByNameIgnoreCase(name)) {
            return stationaryRepository.findAllByNameIgnoreCase(name);
        }else{
            throw new ResourceNotFoundException("The Stationary item with name: "
                    + name + " does not exist");
        }
    }
    private List<Stationary> getAllStationaryByNameContainingIgnoreCase(String name) {
        if (stationaryRepository.existsByNameContainingIgnoreCase(name)) {
            return stationaryRepository.findAllByNameContainingIgnoreCase(name);
        }else{
            throw new ResourceNotFoundException("The Stationary item containing name: "
                    + name + " does not exist");
        }
    }
    public boolean existsById(Stationary stationary){
        return stationaryRepository.existsByBarcode(stationary.getBarcode());
    }

    @Override
    @Validated(OnCreate.class)
    public Stationary save(@Valid Stationary stationary) throws Exception {

        if (!(stationaryRepository.existsByBarcode(stationary.getBarcode()))) {
            log.info("Stationary item: " + stationary.getName() + ", has been saved");
            return stationaryRepository.save(stationary);
        }else throw new Exception("A stationary item with barcode: "+stationary.getBarcode()+" already exists");
    }

    @Override
    @Validated(OnUpdate.class)
    public Optional<Stationary> updateById(Long id, @Valid Stationary stationaryPatch) {
        return getStationaryById(id)
                .map(stationaryInDB -> {
                    patchNullQty(stationaryPatch, stationaryInDB);

                    updateQty(stationaryPatch, stationaryInDB);

                    updateName(stationaryPatch, stationaryInDB);

                    updateBarcode(stationaryPatch, stationaryInDB);

                    updatePrice(stationaryPatch, stationaryInDB);

                    log.info("The stationary item : " + stationaryInDB.getName() + " was updated");
                    return stationaryRepository.save(stationaryInDB);

                //return stationaryInDB;
        });
    }

    public void updateQty(@Valid Stationary stationaryPatch, Stationary stationaryInDB) {
        if(stationaryPatch.getInStockQuantity() >= 0)
            stationaryInDB.setInStockQuantity(stationaryPatch.getInStockQuantity());
    }

    public void updateName(@Valid Stationary stationaryPatch, Stationary stationaryInDB) {
        if (stationaryPatch.getName() != null)
            stationaryInDB.setName(stationaryPatch.getName());
    }

    public void updateBarcode(@Valid Stationary stationaryPatch, Stationary stationaryInDB) {
        if (stationaryPatch.getBarcode() != null)
            stationaryInDB.setBarcode(stationaryPatch.getBarcode());
    }

    public void updatePrice(@Valid Stationary stationaryPatch, Stationary stationaryInDB) {
        if (stationaryPatch.getPrice() != null)
            stationaryInDB.setPrice(stationaryPatch.getPrice());
    }

    public void patchNullQty(@Valid Stationary stationaryPatch, Stationary stationaryInDB) {
        if (stationaryPatch.getInStockQuantity() == null)
            stationaryPatch.setInStockQuantity(stationaryInDB.getInStockQuantity());
    }

    @Override
    public List<Stationary> findAll() {
        return stationaryRepository.findAll();
    }

    @Override
    public List<Stationary> findAllByName(String name){

        return getAllStationaryByNameIgnoreCase(name);
    }

    @Override
    public List<Stationary> findAllByNameContaining(String name){
        return getAllStationaryByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Stationary> findAllInStock() {
        return stationaryRepository.findAll()
                .stream()
                .filter(stationary -> stationary.getInStockQuantity()>=1)
                .collect(Collectors.toList());
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

