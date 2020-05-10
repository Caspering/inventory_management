package com.kasperin.inventory_management.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univocity.parsers.common.record.Record;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class FruitAndVege extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @JsonProperty("fruitAndVege_url")
    private String fruitAndVegeUrl;

    public FruitAndVege(Record record) {
        this.name = record.getString("name");
        this.barcode = record.getString("barcode");
        this.price = record.getDouble("price");
        this.inStockQuantity= record.getInt("qty");
    }

//    public boolean isNew() {
//        return this.id == null;
//    }
//
    @Column
    private String name;

    @Column
    private String barcode;

    @Column
    private Double price;

    @Column
    private int inStockQuantity;

}
