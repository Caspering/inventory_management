package com.kasperin.inventory_management.api.v1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kasperin.inventory_management.domain.Items.OrderedItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAnalysisDto {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @JsonProperty(value = "Total number of Items")
    int totalItemQuantity;

    @JsonProperty(value = "Total Price")
    int totalItemAmount;

    @JsonProperty(value = "Items Purchased")
    List<OrderedItem> items = new ArrayList<>();

    public void addItem(OrderedItem item){
        this.items.add(item);
    }
    public void addItems(List<OrderedItem> items){
        for(OrderedItem item : items){
            this.addItem(item);
        }
    }
    public void removeItem(OrderedItem item){
        this.items.remove(item);
    }
}
