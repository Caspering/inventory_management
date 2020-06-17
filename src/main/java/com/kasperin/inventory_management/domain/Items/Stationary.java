package com.kasperin.inventory_management.domain.Items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kasperin.inventory_management.domain.Items.Item;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.univocity.parsers.common.record.Record;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
//@PrimaryKeyJoinColumn(name = "item_id")
@Validated
public class Stationary extends Item {

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