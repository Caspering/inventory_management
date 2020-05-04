package com.kasperin.inventory_management.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProcessedFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String barcode;

    @Column
    private Double price;

    @Column
    private Integer inStockQuantity;

    @Column
    private Date mfgDate;

    @Column
    private Date expDate;

    @Enumerated(value = EnumType.STRING)
    private FoodType foodType;

}
