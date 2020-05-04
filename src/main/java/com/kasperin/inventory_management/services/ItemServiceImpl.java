package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import com.kasperin.inventory_management.repository.ItemRepo;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService{

    private final FruitAndVegeRepository fruitAndVegeRepository;
    private final ProcessedFoodRepo processedFoodRepo;
    private final ItemRepo itemRepo;

    public ItemServiceImpl(FruitAndVegeRepository fruitAndVegeRepository, ProcessedFoodRepo processedFoodRepo, ItemRepo itemRepo) {
        this.fruitAndVegeRepository = fruitAndVegeRepository;
        this.processedFoodRepo = processedFoodRepo;
        this.itemRepo = itemRepo;
    }


    @Override
    public Map<List<FruitAndVege>, List<ProcessedFood>> findAll() {
        Map<List<FruitAndVege>, List<ProcessedFood>> add = new HashMap<>();
        add.put(fruitAndVegeRepository.findAll(), processedFoodRepo.findAll());
        return add;
    }
}
