package com.kasperin.inventory_management.services.itemsServices;

import com.kasperin.inventory_management.domain.Items.OrderedItem;
import com.kasperin.inventory_management.repository.ItemsRepository.OrderedItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//@Component
@AllArgsConstructor
//@Transactional
public class OrderedItemServiceImpl implements OrderedItemService {

    private final OrderedItemRepository orderedItemRepository;

    @Override
    public OrderedItem save(OrderedItem orderedItem) {
        return orderedItemRepository.save(orderedItem);

    }
}
