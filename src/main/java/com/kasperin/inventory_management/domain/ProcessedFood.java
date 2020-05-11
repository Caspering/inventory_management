package com.kasperin.inventory_management.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univocity.parsers.annotations.EnumOptions;
import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.common.record.Record;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class ProcessedFood extends Item{

    @Transient
    @JsonProperty("processedFood_url")
    private String processedFoodUrl;

    @Parsed(field = "type")
    @EnumOptions(customElement = "toString")
    @Enumerated(value = EnumType.STRING)
    FoodType foodType;

    @Column
    @Parsed(field = "mfg")
    @Format(formats = {"yyyy-MM-dd", "dd/MM/yyyy"}, options = "locale=en;lenient=false")
    private Date mfgDate;

    @Column
    @Parsed(field = "exp")
    @Format(formats = {"yyyy-MM-dd", "dd/MM/yyyy"}, options = "locale=en;lenient=false")
    private Date expDate;

    public ProcessedFood(Record record){
        super.setName(record.getString("name"));
        super.setBarcode(record.getString("barcode"));
        super.setPrice(record.getDouble("price"));
        super.setInStockQuantity(record.getInt("qty"));
//        setFoodType(foodType.toString(record.getString(4)));
//        this.mfgDate = record.getDate("mfg", "dd/MM/yyyy");
//        this.expDate = record.getDate("exp", "dd/MM/yyyy");

    }




}
