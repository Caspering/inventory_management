package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
}
