package com.kasperin.inventory_management.services.itemsServices;

import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.domain.Items.ProcessedFood;
import com.kasperin.inventory_management.domain.Items.Stationary;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
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


    @Scheduled(fixedDelay=300000)
    public void getTotalInStockQuantity() {
        //int totalNumberInStock=0;
        int totalFAVQuantity=0;
        int totalProcessedFoodQuantity=0;
        int totalStationaryQuantity=0;
        List<Object> items = new ArrayList<>();

        items.add(fruitAndVegeService.findAll());
        items.add(processedFoodService.findAll());
        items.add(stationaryService.findAll());

        for(FruitAndVege fruitAndVege : fruitAndVegeService.findAllInStock()){
            log.info("Current in stock quantity for " +fruitAndVege.getName()+" is "
                    +fruitAndVege.getInStockQuantity());
            totalFAVQuantity += fruitAndVege.getInStockQuantity();
        }
        for(ProcessedFood processedFood : processedFoodService.findAllInStock()){
            log.info("Current in stock quantity for " +processedFood.getName()+" is "
                    +processedFood.getInStockQuantity());
            totalProcessedFoodQuantity += processedFood.getInStockQuantity();
        }
        for(Stationary stationary : stationaryService.findAllInStock()){
            log.info("Current in stock quantity for " +stationary.getName()+" is "
                    +stationary.getInStockQuantity());
            totalStationaryQuantity += stationary.getInStockQuantity();
        }
        int totalNumberInStock=totalFAVQuantity+totalProcessedFoodQuantity+totalStationaryQuantity;



        log.info("current total in stock quantity is "+totalNumberInStock);
    }
}
