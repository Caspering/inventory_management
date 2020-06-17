package com.kasperin.inventory_management.domain.Items;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kasperin.inventory_management.CSV.conversions.LocalDateFormatter;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.validator_services.OnCreate;
import com.kasperin.inventory_management.validator_services.OnCreateDateConstraint;
import com.univocity.parsers.annotations.Convert;
import com.univocity.parsers.annotations.EnumOptions;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Validate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
//@PrimaryKeyJoinColumn(name = "item_id")
@OnCreateDateConstraint(
                groups =  OnCreate.class,
                message = "Expiry Date must be ahead of Manufacturing Date")
public class ProcessedFood extends Item {

    /*@ManyToOne
    private PurchaseOrder purchaseOrder;*/

    @Parsed(field = "type")
    @EnumOptions(customElement = "typeCode")
    @Enumerated(value = EnumType.STRING)
    FoodType foodType;

    @Column
    @Validate
    @Parsed(field = "mfg")
    @Convert(conversionClass = LocalDateFormatter.class, args = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate mfgDate;

    @Column
    @Validate
    @Parsed(field = "exp")
    @Convert(conversionClass = LocalDateFormatter.class, args = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expDate;

}




