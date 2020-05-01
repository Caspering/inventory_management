package com.kasperin.inventory_management.bootstrap;

import com.kasperin.inventory_management.model.FruitAndVege;
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

    public FruitAndVegeBootStrap(FruitAndVegeRepository fruitAndVegeRepository) {
        this.fruitAndVegeRepository = fruitAndVegeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        fruitAndVegeRepository.saveAll(getFruitAndVeges());

    }

    private List<FruitAndVege> getFruitAndVeges(){

        List<FruitAndVege> fruitAndVeges = new ArrayList<>();
        fruitAndVeges.add(new FruitAndVege());
        fruitAndVeges.add(new FruitAndVege());
        fruitAndVeges.add(new FruitAndVege());
        return fruitAndVeges;
    }


}
