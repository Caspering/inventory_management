package com.kasperin.inventory_management.domain.Items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name="OrderedItem")
@Data
@NoArgsConstructor
public class OrderedItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private String barcode;

    private Double price;

    private Integer quantity;











    @Transient
    @JsonIgnore
    public double getTotalPrice() {
        return (this.getQuantity() * this.getPrice());
    }


}
