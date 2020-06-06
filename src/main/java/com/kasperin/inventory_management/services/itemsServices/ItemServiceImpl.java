package com.kasperin.inventory_management.services.itemsServices;

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

    @Override
    public List<Object> findAllInStock() {
        List<Object> itemsInStock = new ArrayList<>();

        itemsInStock.add(fruitAndVegeService.findAllInStock());
        itemsInStock.add(processedFoodService.findAllInStock());
        itemsInStock.add(stationaryService.findAllInStock());

        return itemsInStock;
    }
}
