package com.kasperin.inventory_management.domain.Items;

import com.univocity.parsers.common.record.Record;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
//@PrimaryKeyJoinColumn(name = "item_id")
@Validated
public class Stationary extends Item {

    //Csv Importer Constructor
    public Stationary(Record record) {
        super.setName(record.getString("name"));
        super.setBarcode(record.getString("barcode"));
        super.setPrice(record.getDouble("price"));
        super.setInStockQuantity(record.getInt("qty"));
    }
}
