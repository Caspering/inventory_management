package com.kasperin.inventory_management.domain.Items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kasperin.inventory_management.validator_services.OnCreate;
import com.kasperin.inventory_management.validator_services.OnUpdate;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Validate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
//@Entity(name = "Item")
@MappedSuperclass
@Slf4j
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(groups = OnCreate.class,
             message = "The item must be given a name")
    @Size(min=1,
             groups = {OnUpdate.class, OnCreate.class},
             message = "The item must be given a name")
    @Column
    @Parsed
    @Validate
    private String name;

    @NotNull(groups = OnCreate.class,
            message = "The item must be given a unique barcode")//TODO check and constrain empty string
    @Size(min=1,
            groups = {OnUpdate.class,OnCreate.class},
            message = "The item must be given a barcode")
    @Parsed
    @Validate
    private String barcode;

    @Min(value = 0,
            groups = {OnUpdate.class,OnCreate.class},
            message = "Item must have at least {value} or more price")
    @Column
    @Parsed
    private Double price;

    @Min(value = 0,
            groups = {OnUpdate.class,OnCreate.class},
            message = "Item must have at least {value} or more stationary inStockQuantity")
    @Column
    @Parsed(field = "qty")
    //@NullString(nulls = {"", "N/A"})
    private Integer inStockQuantity;



    @Transient
    @JsonIgnore
    public double getTotalPrice() {
        return (this.getInStockQuantity() * this.getPrice());
    }


}
