package com.kasperin.inventory_management.domain.Items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kasperin.inventory_management.domain.Discount;
import com.univocity.parsers.common.record.Record;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
//@PrimaryKeyJoinColumn(name = "item_id")
@Validated
public class Stationary extends Item {

    @ManyToOne//(mappedBy = "fruitAndVeges")
    @JsonIgnore
    private Discount discount;

   /* @JsonIgnore
    @ManyToOne
    private PurchaseOrder purchaseOrder;*/

    //Csv Importer Constructor
    public Stationary(Record record) {
        super.setName(record.getString("name"));
        super.setBarcode(record.getString("barcode"));
        super.setPrice(record.getDouble("price"));
        super.setInStockQuantity(record.getInt("qty"));
    }

}
