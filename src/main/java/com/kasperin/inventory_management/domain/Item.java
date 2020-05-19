package com.kasperin.inventory_management.domain;

import com.kasperin.inventory_management.validator_services.OnCreate;
import com.kasperin.inventory_management.validator_services.OnUpdate;
import com.univocity.parsers.annotations.Parsed;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(groups = OnCreate.class,
    message = "The item must be given a name")
    @Column
    @Parsed
    private String name;

    @Column
    @Parsed
    private String barcode;

    @Column
    @Parsed
    private Double price;


    @Min(value = 1, groups = OnCreate.class,
         message = "There must be at least {value} count of in stock quantity for a stationary item")
    @Min(value = 0, groups = OnUpdate.class,
         message = "You must update with at least 0 or more stationary inStockQuantity")
    @Column
    @Parsed(field = "qty")
    private Integer inStockQuantity;

}
