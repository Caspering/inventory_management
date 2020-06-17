package com.kasperin.inventory_management.domain.Items;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "OrderedItem_id")
public class OrderedFruitAndVegeItem extends OrderedItem{
    public OrderedFruitAndVegeItem(FruitAndVege fruitAndVege) {
        super.setName(fruitAndVege.getName());
        super.setBarcode(fruitAndVege.getBarcode());
        super.setQuantity(fruitAndVege.getInStockQuantity());
        super.setPrice(fruitAndVege.getPrice());
    }
}
