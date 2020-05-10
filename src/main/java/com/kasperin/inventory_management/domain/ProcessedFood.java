package com.kasperin.inventory_management.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
//@Builder
@Entity
public class ProcessedFood extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column
//    private String name;
//
//    @Column
//    private String barcode;
//
//    @Column
//    private Double price;
//
//    @Column
//    private Integer inStockQuantity;

    @Enumerated(value = EnumType.STRING)
    private FoodType foodType;

    @Column
    private Date mfgDate;

    @Column
    private Date expDate;

    @Transient
    @JsonProperty("processedFood_url")
    private String processedFoodUrl;

}
