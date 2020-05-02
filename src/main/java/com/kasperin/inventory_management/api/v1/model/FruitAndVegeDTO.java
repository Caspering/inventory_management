package com.kasperin.inventory_management.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FruitAndVegeDTO {

    private Long id;

    private String Name;

    private String barcode;

    private Double price;

    private Integer InStockQuantity;

    @JsonProperty("fruitAndVege_url")
    private String fruitAndVegeUrl;

}
