package com.kasperin.inventory_management.services.itemsServices;

import com.kasperin.inventory_management.domain.Items.OrderedItem;

public interface OrderedItemService {
    OrderedItem save(OrderedItem orderedItem);
}
