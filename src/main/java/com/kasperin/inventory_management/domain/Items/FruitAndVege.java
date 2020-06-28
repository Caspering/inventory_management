package com.kasperin.inventory_management.domain.Items;

import com.univocity.parsers.common.record.Record;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
//@PrimaryKeyJoinColumn(name = "item_id")
@NoArgsConstructor
public class FruitAndVege extends Item {

    //Csv Importer
    public FruitAndVege(Record record) {
        super.setName(record.getString("name"));
        super.setBarcode(record.getString("barcode"));
        super.setPrice(record.getDouble("price"));
        super.setInStockQuantity(record.getInt("qty"));
    }

}

