package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.controllers.v1.FruitAndVegeController;
import com.kasperin.inventory_management.controllers.v1.ProcessedFoodController;
import com.kasperin.inventory_management.controllers.v1.StationaryController;

import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import com.kasperin.inventory_management.repository.StationaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final FruitAndVegeRepository fruitAndVegeRepository;
    private final ProcessedFoodRepo processedFoodRepo;
    private final StationaryRepository stationaryRepository;
    private final StationaryService stationaryService;

 /*   private String getFruitAndVegeUrl(Long id) {
        return FruitAndVegeController.BASE_URL + "/id/" + id;
    }

    private String getProcessedFoodUrl(Long id) {
        return ProcessedFoodController.BASE_URL + "/id/" + id;
    }

    private String getStationaryUrl(Long id) {
        return StationaryController.BASE_URL + "/id/" + id;
    }
  */

    @Override
    public List<Object> findAll() {

        List<Object> items = new ArrayList<>();

        items.add(fruitAndVegeRepository.findAll());
        items.add(processedFoodRepo.findAll());
        items.add(stationaryService.findAll());

        return items;

    }
}