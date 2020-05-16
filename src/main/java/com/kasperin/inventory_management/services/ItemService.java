package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.Item;
import com.kasperin.inventory_management.domain.ItemList;

import java.util.List;

public interface ItemService {

    List<Object> findAll();
}
