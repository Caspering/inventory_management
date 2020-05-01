package com.kasperin.inventory_management.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public boolean isNew() {
        return this.id == null;
    }

    @Column
    private String name;

    @Column
    private long barcode;

    @Column
    private double price;

    @Column
    private int InStockQuantity;


}
