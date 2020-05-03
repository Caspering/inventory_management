package com.kasperin.inventory_management.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
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
    private Integer InStockQuantity;

    @Column
    private Date mfgDate;

    @Column
    private Date expDate;

    @Enumerated(value = EnumType.STRING)
    private FoodType foodType;


}
