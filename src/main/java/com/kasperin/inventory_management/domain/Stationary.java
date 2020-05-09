package com.kasperin.inventory_management.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Parsed;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class Stationary{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    //@Parsed(index = 0)
    private String name;

    @Column
    //@Parsed(index = 1)
    private String barcode;

    @Column
    //@Parsed(index = 2)
    private Double price;

    @Column
    //@Parsed(index = 3)
    private Integer InStockQuantity;
}
