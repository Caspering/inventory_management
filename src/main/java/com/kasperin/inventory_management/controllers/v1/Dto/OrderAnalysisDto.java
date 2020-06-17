package com.kasperin.inventory_management.controllers.v1.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kasperin.inventory_management.domain.Items.OrderedItem;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
//@Service
//@Bean
//@RequiredArgsConstructor
//@NoArgsConstructor
//@NoArgsConstructor
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

    /*public int countItems(){
        int count = 0;
        List<OrderedItem> items = getItems();
        for(OrderedItem item : items){
            count += item.getQuantity();
        }
        return count;
    }

    public Double getTotalPrice(){
        double sum = 0;
        List<OrderedItem> items = getItems();
        for (OrderedItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;

    }*/

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
