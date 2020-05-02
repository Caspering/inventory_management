package com.kasperin.inventory_management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table
public class FruitAndVege extends BaseEntity{
    public FruitAndVege(Long id, String name, String barcode, double price, int InStockQuantity) {
        super(id, name, barcode, price, InStockQuantity);
    }
}
