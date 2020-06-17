package com.kasperin.inventory_management.domain.Items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.univocity.parsers.common.record.Record;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Set;

@Data
@Entity
//@PrimaryKeyJoinColumn(name = "item_id")
@NoArgsConstructor
public class FruitAndVege extends Item {

    //@ManyToOne//(mappedBy = "fruitAndVeges")
    //@JsonIgnore
   // private PurchaseOrder purchaseOrder;



    //Csv Importer
    public FruitAndVege(Record record) {
        super.setName(record.getString("name"));
        super.setBarcode(record.getString("barcode"));
        super.setPrice(record.getDouble("price"));
        super.setInStockQuantity(record.getInt("qty"));
    }

}

