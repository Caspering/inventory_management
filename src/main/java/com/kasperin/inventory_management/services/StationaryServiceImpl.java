package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.repository.StationaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class StationaryServiceImpl implements StationaryService{

    private final StationaryRepository stationaryRepository;

    public StationaryServiceImpl(StationaryRepository stationaryRepository) {
        this.stationaryRepository = stationaryRepository;
    }

    @Override
    public List<Stationary> findAll() {
        return stationaryRepository.findAll();
    }


    //just experimenting
        @Override
    public Stationary findByName(String name) {
        return stationaryRepository.findByName(name).orElseThrow(ResourceNotFoundException::new);
    }


    //works fine, just experimenting
//    @Override
//    public Optional<Stationary> findByName(String name) {
//        return stationaryRepository.findByName(name);
//    }

    @Override
    public Optional<Stationary> findById(Long id) {
        return stationaryRepository.findById(id);
    }

    @Override
    public Stationary save(Stationary stationary) {
        stationaryRepository.save(stationary);
        log.info("Stationary item: " + stationary.getName() + ", has been saved");
        return stationary;
    }


    @Override
    public void deleteById(Long id) {
            stationaryRepository.deleteById(id);
    }
}
