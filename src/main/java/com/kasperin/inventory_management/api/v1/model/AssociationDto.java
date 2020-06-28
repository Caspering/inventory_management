package com.kasperin.inventory_management.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssociationDto {

    private String keyBarcode;
    private String associatedBarcode;


}
