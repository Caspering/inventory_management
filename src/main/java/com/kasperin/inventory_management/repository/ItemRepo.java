package com.kasperin.inventory_management.repository;

import com.kasperin.inventory_management.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}
