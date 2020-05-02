package com.kasperin.inventory_management.api.v1.model;

import com.kasperin.inventory_management.domain.FruitAndVege;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
public class FruitAndVegeListDTO {

    List<FruitAndVege> fruitAndVeges;

}
