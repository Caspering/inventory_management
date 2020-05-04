package com.kasperin.inventory_management.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
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
    private String barcode;

    @Column
    private Double price;

    @Column
    private Integer inStockQuantity;


}
