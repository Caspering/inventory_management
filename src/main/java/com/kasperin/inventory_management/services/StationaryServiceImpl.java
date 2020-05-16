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
@Slf4j
@Service
@AllArgsConstructor
public class StationaryServiceImpl implements StationaryService{

    private final StationaryRepository stationaryRepository;

    private String getStationaryUrl(Long id) {
        return StationaryController.BASE_URL + "/id/" + id;
    }

    @Override
    public Stationary save(Stationary stationary) {
        Stationary savedSt = stationaryRepository.save(stationary);

        savedSt.setStationaryUrl(getStationaryUrl(savedSt.getId()));

        log.info("Stationary item: " + stationary.getName() + ", has been saved");

        return savedSt;
    }

    @Override
    public Optional<Stationary> updateById(Long id, Stationary st1) {
        return stationaryRepository.findById(id).map(stationary -> {

        if(st1.getInStockQuantity() != 0){
                stationary.setInStockQuantity(st1.getInStockQuantity());
        }
        if(st1.getName() != null){
            stationary.setName(st1.getName());
        }
        if(st1.getBarcode() != null){
            stationary.setBarcode(st1.getBarcode());
        }
        if(st1.getPrice() != null){
            stationary.setPrice(st1.getPrice());
        }

           return stationaryRepository.save(stationary);
        });
    }


    @Override
    public List<Stationary> findAll() {
        List<Stationary> st = stationaryRepository.findAll();
        st.removeIf(s -> s.getInStockQuantity() == 0);
        return st;
    }

    @Override
    public Stationary findByName(String name) {
        Stationary st = stationaryRepository.findByName(name);
        st.setStationaryUrl(getStationaryUrl(st.getId()));
        return st;
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
