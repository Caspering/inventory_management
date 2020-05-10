package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.domain.Item;
import com.kasperin.inventory_management.domain.ProcessedFood;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
}
