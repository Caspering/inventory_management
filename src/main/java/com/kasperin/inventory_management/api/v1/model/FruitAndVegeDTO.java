package com.kasperin.inventory_management.api.v1.model;

import com.kasperin.inventory_management.validator_services.OnCreate;
import com.kasperin.inventory_management.validator_services.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FruitAndVegeDTO {

    private Long id;

    private String Name;

    private String barcode;

    private Double price;

    @Min(value = 1, groups = OnCreate.class,
            message = "There must be at least {value} count " +
                    "of in stock quantity for a stationary item"
    )
    @Min(value = 0, groups = OnUpdate.class,
            message = "You must update with at least 0 or more " +
                    "stationary inStockQuantity"
    )
    private Integer InStockQuantity;

}
