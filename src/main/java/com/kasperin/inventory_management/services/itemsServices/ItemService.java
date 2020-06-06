package com.kasperin.inventory_management.services.itemsServices;

import java.util.List;

public interface ItemService {

    List<Object> findAll();

    List<Object> findAllInStock();
}
