package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.model.FruitAndVege;

import java.util.List;
import java.util.Optional;

public interface FruitAndVegeService {

    FruitAndVege createNewFruitAndVege(FruitAndVege fruitAndVege);

    Optional<FruitAndVege> getFruitAndVegeById(Long id);

    FruitAndVege getFruitAndVegeByName(String name);

    List<FruitAndVege> getAllFruitAndVeges();

    FruitAndVege updateFruitAndVege(Long id, FruitAndVege fruitAndVege);

    void deleteFruitAndVegeById(Long id);

}
