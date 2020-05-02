package com.kasperin.inventory_management.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class FruitAndVege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public boolean isNew() {
        return this.id == null;
    }

    @Column
    private String name;

    @Column
    private String barcode;

    @Column
    private Double price;

    @Column
    private Integer InStockQuantity;

}
