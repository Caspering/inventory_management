package com.kasperin.inventory_management.domain.Items;

import com.kasperin.inventory_management.domain.enums.FoodType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "OrderedItem_id")
public class OrderedProcessedFoodItem extends OrderedItem{

    @Enumerated(value = EnumType.STRING)
    FoodType foodType;

    private LocalDate mfgDate;

    private LocalDate expDate;

    public OrderedProcessedFoodItem(ProcessedFood processedFood){
        super.setName(processedFood.getName());
        super.setBarcode(processedFood.getBarcode());
        super.setQuantity(processedFood.getInStockQuantity());
        super.setPrice(processedFood.getPrice());
        this.expDate = processedFood.getExpDate();
        this.mfgDate = processedFood.getMfgDate();
        this.foodType = processedFood.getFoodType();
    }
}
