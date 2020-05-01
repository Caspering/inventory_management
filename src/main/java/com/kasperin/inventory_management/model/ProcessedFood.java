package com.kasperin.inventory_management.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class ProcessedFood extends BaseEntity{

    @Column
    private Date mfgDate;

    @Column
    private Date expDate;


}
