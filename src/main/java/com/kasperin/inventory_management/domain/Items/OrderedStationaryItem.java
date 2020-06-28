package com.kasperin.inventory_management.domain.Items;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("JpaDataSourceORMInspection")
@Data
@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "OrderedItem_id")
public class OrderedStationaryItem extends OrderedItem{
    public OrderedStationaryItem(Stationary stationary){
        super.setName(stationary.getName());
        super.setBarcode(stationary.getBarcode());
        super.setQuantity(stationary.getInStockQuantity());
        super.setPrice(stationary.getPrice());
    }
}
