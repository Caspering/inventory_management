package com.kasperin.inventory_management.domain;

import com.kasperin.inventory_management.CSV.conversions.LocalDateFormatter;
import com.univocity.parsers.annotations.Convert;
import com.univocity.parsers.annotations.EnumOptions;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Validate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

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




