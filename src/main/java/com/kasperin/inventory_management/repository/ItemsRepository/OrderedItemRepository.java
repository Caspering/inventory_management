package com.kasperin.inventory_management.repository.ItemsRepository;

import com.kasperin.inventory_management.domain.Items.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
}
