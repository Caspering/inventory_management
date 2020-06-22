package com.kasperin.inventory_management.services.itemsServices;

import com.kasperin.inventory_management.api.v1.model.OrderAnalysisDto;
import com.kasperin.inventory_management.domain.Items.OrderedItem;

public interface OrderedItemService {
    OrderedItem save(OrderedItem orderedItem);

    OrderAnalysisDto analyzeOrdersByDate(OrderedItemServiceImpl.OrderAnalysisForm Form);
}
