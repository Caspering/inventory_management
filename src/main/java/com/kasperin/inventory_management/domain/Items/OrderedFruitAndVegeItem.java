package com.kasperin.inventory_management.domain.Items;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "OrderedItem_id")
public class OrderedFruitAndVegeItem extends OrderedItem{
    public OrderedFruitAndVegeItem(FruitAndVege fruitAndVege) {
        super.setName(fruitAndVege.getName());
        super.setBarcode(fruitAndVege.getBarcode());
        super.setQuantity(fruitAndVege.getInStockQuantity());
        super.setPrice(fruitAndVege.getPrice());
    }
}
