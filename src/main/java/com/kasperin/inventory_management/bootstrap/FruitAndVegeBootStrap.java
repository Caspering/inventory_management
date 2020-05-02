package com.kasperin.inventory_management.bootstrap;

import com.kasperin.inventory_management.model.FruitAndVege;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FruitAndVegeBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final FruitAndVegeRepository fruitAndVegeRepository;

    public FruitAndVegeBootStrap(FruitAndVegeRepository fruitAndVegeRepository) {
        this.fruitAndVegeRepository = fruitAndVegeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        fruitAndVegeRepository.saveAll(getFruitAndVeges());

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
