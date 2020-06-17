package com.kasperin.inventory_management.services.itemsServices;

import com.kasperin.inventory_management.controllers.v1.Dto.OrderAnalysisDto;
import com.kasperin.inventory_management.domain.Items.OrderedItem;

public interface OrderedItemService {
    OrderedItem save(OrderedItem orderedItem);

    OrderAnalysisDto calculate(OrderedItemServiceImpl.OrderAnalysisForm Form);
}
