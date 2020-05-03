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

    private List<ProcessedFood> getProcessedFood() {

        List<ProcessedFood> proFood = new ArrayList<>();
            
        ProcessedFood savedProcessedFood = new ProcessedFood();
//        savedProcessedFood.setId(1L);
        savedProcessedFood.setName("Chips");
        savedProcessedFood.setBarcode("12345");
        savedProcessedFood.setPrice(1.3);
        savedProcessedFood.setFoodType(FoodType.VEGAN);
        
        proFood.add(savedProcessedFood);


        ProcessedFood proFood2 = new ProcessedFood();
//        proFood2.setId(1L);
        proFood2.setName("burger");
        proFood2.setBarcode("12345");
        proFood2.setPrice(1.3);
        proFood2.setFoodType(FoodType.VEGAN);

        proFood.add(proFood2);

        ProcessedFood proFood3 = new ProcessedFood();
//        proFood3.setId(1L);
        proFood3.setName("fries");
        proFood3.setBarcode("12345");
        proFood3.setPrice(1.3);
        proFood3.setFoodType(FoodType.NONVEGAN);

        proFood.add(proFood3);
        
        return proFood;

    }    
}
