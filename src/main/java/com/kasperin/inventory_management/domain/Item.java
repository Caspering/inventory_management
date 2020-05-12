package com.kasperin.inventory_management.domain;

import com.univocity.parsers.annotations.Parsed;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Parsed
    private String name;

    @Column
    @Parsed
    private String barcode;

    @Column
    @Parsed
    private Double price;

    @Column
    @Parsed(field = "qty")
    private int inStockQuantity;

}
