package com.kasperin.inventory_management.domain;

import com.kasperin.inventory_management.CSV.conversions.LocalDateFormatter;
import com.univocity.parsers.annotations.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class ProcessedFood extends Item{

    @Parsed(field = "type")
    @EnumOptions(customElement = "typeCode")
    @Enumerated(value = EnumType.STRING)
    FoodType foodType;

    @Column
    @Validate
    @Parsed(field = "mfg")
    @Convert(conversionClass = LocalDateFormatter.class, args = "dd/MM/yyyy")
    private LocalDate mfgDate;

    @Column
    @Validate
    @Parsed(field = "exp")
    @Convert(conversionClass = LocalDateFormatter.class, args = "dd/MM/yyyy")
    private LocalDate expDate;

}

//    @Transient
//    @JsonProperty("processedFood_url")
//    private String processedFoodUrl;



