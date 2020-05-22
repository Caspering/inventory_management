package com.kasperin.inventory_management.domain;

import com.univocity.parsers.annotations.EnumOptions;
import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.Parsed;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Parsed(field = "mfg")
    @Format(formats = {"yyyy-MM-dd", "dd/MM/yyyy","yyyy/MM/dd", "dd-MM-yyyy"}) // options = "locale=en;lenient=false")
    private Date mfgDate;

    @Column
    @Parsed(field = "exp")
    @Format(formats = {"yyyy-MM-dd", "dd/MM/yyyy","yyyy/MM/dd", "dd-MM-yyyy"}) //options = "locale=en;lenient=false")
    private Date expDate;

}

//    @Transient
//    @JsonProperty("processedFood_url")
//    private String processedFoodUrl;



