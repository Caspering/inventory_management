package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.domain.*;
import com.kasperin.inventory_management.services.ItemService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}
