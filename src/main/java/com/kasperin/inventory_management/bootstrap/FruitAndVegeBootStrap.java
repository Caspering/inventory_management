package com.kasperin.inventory_management.bootstrap;

import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FruitAndVegeBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final FruitAndVegeRepository fruitAndVegeRepository;

    private final ProcessedFoodRepo processedFoodRepo;

    public FruitAndVegeBootStrap(FruitAndVegeRepository fruitAndVegeRepository, ProcessedFoodRepo processedFoodRepo) {
        this.fruitAndVegeRepository = fruitAndVegeRepository;
        this.processedFoodRepo = processedFoodRepo;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        fruitAndVegeRepository.saveAll(getFruitAndVeges());
        processedFoodRepo.saveAll(getProcessedFood());

    }

    private List<FruitAndVege> getFruitAndVeges(){

        List<FruitAndVege> fruitAndVeges = new ArrayList<>();
        FruitAndVege fav1 = new FruitAndVege();
        fav1.setName("fav1");
        fav1.setBarcode("123");
        fav1.setInStockQuantity(5);
        fav1.setPrice(2.99);

        fruitAndVeges.add(fav1);
        
        FruitAndVege fruitAndVege2 = new FruitAndVege();
        fruitAndVege2.setName("Orange");
        fruitAndVege2.setBarcode("123456");
        fruitAndVege2.setInStockQuantity(6);
        fruitAndVege2.setPrice(3.8);
        
        fruitAndVeges.add(fruitAndVege2);

        FruitAndVege fruitAndVege3 = new FruitAndVege();
        fruitAndVege3.setName("Pineapple");
        fruitAndVege3.setBarcode("133006");
        fruitAndVege3.setInStockQuantity(8);
        fruitAndVege3.setPrice(3.8);

        fruitAndVeges.add(fruitAndVege3);
        
        return fruitAndVeges;
    }
}
