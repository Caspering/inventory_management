package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.domain.ProcessedFood;

import java.util.List;
import java.util.Map;

public interface ItemService {

    Map<List<FruitAndVege>, List<ProcessedFood>> findAll();

}
