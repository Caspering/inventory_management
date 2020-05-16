package com.kasperin.inventory_management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ItemList {

    List<FruitAndVege> fruitAndVegeList;
    List<ProcessedFood> processedFoodList;
    List<Stationary> stationaryList;
}
