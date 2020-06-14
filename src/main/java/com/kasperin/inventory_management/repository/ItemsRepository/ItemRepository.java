package com.kasperin.inventory_management.repository.ItemsRepository;

import com.kasperin.inventory_management.domain.Items.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
