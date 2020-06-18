package com.kasperin.inventory_management.services.itemsServices;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kasperin.inventory_management.controllers.v1.Dto.OrderAnalysisDto;
import com.kasperin.inventory_management.domain.Items.OrderedItem;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.repository.ItemsRepository.OrderedItemRepository;
import com.kasperin.inventory_management.services.commerceServices.PurchaseOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@AllArgsConstructor
//@NoArgsConstructor
public class OrderedItemServiceImpl implements OrderedItemService {

    private final OrderedItemRepository orderedItemRepository;
    private final PurchaseOrderService purchaseOrderService;
    //private final OrderAnalysisDto orderAnalysisDto;

    /*public OrderedItemServiceImpl(OrderedItemRepository orderedItemRepository, PurchaseOrderService purchaseOrderService, OrderAnalysisDto orderAnalysisDto) {
        this.orderedItemRepository = orderedItemRepository;
        this.purchaseOrderService = purchaseOrderService;
        this.orderAnalysisDto = orderAnalysisDto;
    }*/

    @Override
    public OrderedItem save(OrderedItem orderedItem) {
        return orderedItemRepository.save(orderedItem);
    }

    @Override
    public OrderAnalysisDto analyzeOrdersByDate(OrderAnalysisForm form){

        OrderAnalysisDto orderAnalysisDto = new OrderAnalysisDto();
        orderAnalysisDto.setStartDate(form.getStartDate());
        orderAnalysisDto.setEndDate(form.getEndDate());
        int totalCost=0;
        int totalQuantity=0;
        for (PurchaseOrder purchaseOrder : purchaseOrderService
                .findAllByDateCreatedBetween(form.getStartDate(), form.getEndDate())) {
            totalQuantity += purchaseOrder.getTotalNumberOfItems();
            totalCost += purchaseOrder.getTotalPrice();
            orderAnalysisDto.addItems(purchaseOrder.getItems());
        }
        orderAnalysisDto.setTotalItemQuantity(totalQuantity);
        orderAnalysisDto.setTotalItemAmount(totalCost);
        return orderAnalysisDto;
    }

    public  static class OrderAnalysisForm{
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy")
        public LocalDate startDate;

        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy")
        public LocalDate endDate;

        public LocalDate getStartDate() {
            return startDate;
        }
        public LocalDate getEndDate() {
            return endDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }
        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }
    }
}
