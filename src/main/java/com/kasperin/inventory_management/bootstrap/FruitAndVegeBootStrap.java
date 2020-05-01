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

        fruitAndVeges.add(new FruitAndVege());
        fruitAndVeges.add(new FruitAndVege());
        return fruitAndVeges;
    }


}
