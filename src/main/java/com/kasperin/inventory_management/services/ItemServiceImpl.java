package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.Item;
import com.kasperin.inventory_management.repository.ItemRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;

    @Override
    public List<Item> findAll() {
        return itemRepo.findAll();
    }
}
