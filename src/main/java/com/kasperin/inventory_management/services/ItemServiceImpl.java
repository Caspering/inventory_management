package com.kasperin.inventory_management.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final StationaryService stationaryService;
    private final ProcessedFoodService processedFoodService;
    private final FruitAndVegeService fruitAndVegeService;

    @Override
    public List<Object> findAll() {

        List<Object> items = new ArrayList<>();

        items.add(fruitAndVegeService.findAll());
        items.add(processedFoodService.findAll());
        items.add(stationaryService.findAll());

        return items;

    }

}
