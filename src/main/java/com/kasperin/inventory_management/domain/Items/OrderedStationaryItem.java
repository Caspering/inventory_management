package com.kasperin.inventory_management.domain.Items;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "OrderedItem_id")
public class OrderedStationaryItem extends OrderedItem{
    public OrderedStationaryItem(Stationary stationary){
        super.setName(stationary.getName());
        super.setBarcode(stationary.getBarcode());
        super.setQuantity(stationary.getInStockQuantity());
        super.setPrice(stationary.getPrice());
    }
}
