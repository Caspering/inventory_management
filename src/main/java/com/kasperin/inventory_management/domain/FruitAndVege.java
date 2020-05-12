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

    @Transient
    @JsonProperty("fruitAndVege_url")
    private String fruitAndVegeUrl;

    //Csv Importer Constructor
    public FruitAndVege(Record record) {
        super.setName(record.getString("name"));
        super.setBarcode(record.getString("barcode"));
        super.setPrice(record.getDouble("price"));
        super.setInStockQuantity(record.getInt("qty"));
    }

}
