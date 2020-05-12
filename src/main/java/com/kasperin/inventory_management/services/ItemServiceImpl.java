package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.controllers.v1.FruitAndVegeController;
import com.kasperin.inventory_management.controllers.v1.ProcessedFoodController;
import com.kasperin.inventory_management.controllers.v1.StationaryController;
import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.domain.Item;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.repository.ItemRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;

    private String getFruitAndVegeUrl(Long id) {
        return FruitAndVegeController.BASE_URL + "/id/" + id;
    }

    private String getProcessedFoodUrl(Long id) {
        return ProcessedFoodController.BASE_URL + "/id/" + id;
    }

    private String getStationaryUrl(Long id) {
        return StationaryController.BASE_URL + "/id/" + id;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = itemRepo.findAll();
        for(Item i : items){
            if (i instanceof FruitAndVege){
                ((FruitAndVege) i).setFruitAndVegeUrl(getFruitAndVegeUrl(i.getId()));
            }else if (i instanceof ProcessedFood){
                ((ProcessedFood) i).setProcessedFoodUrl(getProcessedFoodUrl(i.getId()));
            }else if (i instanceof Stationary){
                ((Stationary) i).setStationaryUrl(getStationaryUrl(i.getId()));
            }
        }
        return items;
    }
}
