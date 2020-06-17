package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.controllers.PurchaseOrderResourceAssembler;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.services.ResourceNotFoundException;
import com.kasperin.inventory_management.services.commerceServices.PurchaseOrderService;
import com.kasperin.inventory_management.services.commerceServices.PurchaseOrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(PurchaseOrderController.BASE_URL)
@RequiredArgsConstructor
public class PurchaseOrderController {

    public static final String BASE_URL = "/api/v1/purchase_orders";
    private final PurchaseOrderService purchaseOrderService;
    private final PurchaseOrderResourceAssembler assembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseOrder> getAll(){
        return purchaseOrderService.findAll();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    EntityModel<PurchaseOrder> getById(@PathVariable Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order with id: " + id+ " was Not Found"));;

        return assembler.toModel(purchaseOrder);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrder save(@RequestBody PurchaseOrderServiceImpl.OrderForm form) {
        /*PurchaseOrder purchaseOrder = new PurchaseOrder();
        if(form.getPurchaseOrderItemDto().getPaymentType() != null)
        purchaseOrder.setPaymentType(form.getPurchaseOrderItemDto().getPaymentType());
        if(form.getPurchaseOrderItemDto().getMemberNumber() != null){
            purchaseOrder.setMemberNumber(form
                                          .getPurchaseOrderItemDto()
                                          .getMemberNumber());
            purchaseOrder.setMember(memberRepository
                                    .findByMemberNumberIgnoreCase(form
                                    .getPurchaseOrderItemDto()
                                    .getMemberNumber()));

        }

        if(!itemListIsEmptyOrNull(form)){
            for(Map.Entry<String, Integer> entry: form.purchaseOrderItemDto.getItems().entrySet()){
                if (orderedItemExistsInStationaryRepository(entry)){
                    if(requestedStationaryAmountIsAvailable(entry)) {
                        if (requestedStationaryAmountIsGreaterThanZero(entry)) {
                            OrderedItem orderedItem = createOrderedStationaryItem(entry);
                            setOrderedItemAmount(entry, orderedItem);
                            updateInventoryStationaryItemInStockQty(entry);
                            purchaseOrder.addItem(orderedItem);
                        }else throw new RuntimeException("Requested Stationary amount for: "
                                + requestedStationaryName(entry)
                                +" have to be greater than zero");
                    }else throw new RuntimeException("Requested Stationary item amount for: "
                            + requestedStationaryName(entry)
                            +" is not available");
                }
                else if (orderedItemExistsInFruitAndVegeRepository(entry)){
                    if(requestedFruitAndVegeAmountIsAvailable(entry)) {
                        if (requestedFruitAndVegeAmountIsGreaterThanZero(entry)) {
                            OrderedItem orderedItem = createOrderedFruitAndVegeItem(entry);
                            setOrderedItemAmount(entry, orderedItem);
                            updateInventoryFruitAndVegeItemInStockQty(entry);
                            purchaseOrder.addItem(orderedItem);
                        }else throw new RuntimeException("Requested FruitAndVege amount for: "
                                + requestedFruitAndVegeName(entry)
                                +" have to be greater than zero");
                    }else throw new RuntimeException("Requested FruitAndVege item amount for: "
                            + requestedFruitAndVegeName(entry)
                            +" is not available");
                }
                else if (orderedItemExistsInProcessedFoodRepository(entry)){
                    if(requestedProcessedFoodAmountIsAvailable(entry)) {
                        if (requestedProcessedFoodAmountIsGreaterThanZero(entry)) {
                            OrderedItem orderedItem = createOrderedProcessedFoodItem(entry);
                            setOrderedItemAmount(entry, orderedItem);
                            updateInventoryProcessedFoodItemInStockQty(entry);
                            purchaseOrder.addItem(orderedItem);
                        }else throw new RuntimeException("Requested ProcessedFood amount for: "
                                + requestedProcessedFoodName(entry)
                                +" have to be greater than zero");
                    }else throw new RuntimeException("Requested ProcessedFood item amount for: "
                            + requestedProcessedFoodName(entry)
                            +" is not available");
                }else throw new RuntimeException("Item "+entry.getKey()+" was not found in inventory");
            }
        }else throw new RuntimeException("Purchase order must contain an item");*/
        /*return purchaseOrderService.save(purchaseOrder);*/
        return purchaseOrderService.save(form);
    }

    @PatchMapping({"/{id}"})
    public Optional<PurchaseOrder> updatePurchaseOrderDetailsById(@PathVariable Long id,
                                                                  @RequestBody PurchaseOrder purchaseOrder){
        return purchaseOrderService.updatePurchaseOrderDetailsById(id,purchaseOrder);
    }

    @DeleteMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        purchaseOrderService.deleteById(id);
    }




    //Helper Methods for building a purchase order

    /*public static class OrderForm{

        private PurchaseOrderItemDto purchaseOrderItemDto;

        public void setPurchaseOrderItemDto(PurchaseOrderItemDto p){
            this.purchaseOrderItemDto = p;
        }

        public PurchaseOrderItemDto getPurchaseOrderItemDto(){
            return purchaseOrderItemDto;
        }
    }*/

    /*public boolean itemListIsEmptyOrNull(@RequestBody OrderForm form) {
        return org.springframework.util.ObjectUtils.isEmpty(form.purchaseOrderItemDto.getItems());
    }

    public boolean requestedStationaryAmountIsGreaterThanZero(Map.Entry<String, Integer> entry) {
        return OrderedItemAmountRequested(entry)>0;
    }
    public boolean requestedStationaryAmountIsAvailable(Map.Entry<String, Integer> entry) {
        return getInventoryStationaryItemInStockQuantity(entry) >= OrderedItemAmountRequested(entry);
    }

    public boolean requestedFruitAndVegeAmountIsGreaterThanZero(Map.Entry<String, Integer> entry) {
        return OrderedItemAmountRequested(entry)>0;
    }
    public boolean requestedFruitAndVegeAmountIsAvailable(Map.Entry<String, Integer> entry) {
        return getInventoryFruitAndVegeItemInStockQuantity(entry) >= OrderedItemAmountRequested(entry);
    }

    public boolean requestedProcessedFoodAmountIsGreaterThanZero(Map.Entry<String, Integer> entry) {
        return OrderedItemAmountRequested(entry)>0;
    }
    public boolean requestedProcessedFoodAmountIsAvailable(Map.Entry<String, Integer> entry) {
        return getInventoryProcessedFoodItemInStockQuantity(entry) >= OrderedItemAmountRequested(entry);
    }

    public Integer OrderedItemAmountRequested(Map.Entry<String, Integer> entry) {
        return entry.getValue();
    }
    public void setOrderedItemAmount(Map.Entry<String, Integer> entry, OrderedItem orderedItem) {
        orderedItem.setQuantity(OrderedItemAmountRequested(entry));
    }

    public Integer getInventoryStationaryItemInStockQuantity(Map.Entry<String, Integer> entry) {
        return stationaryRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getInStockQuantity();
    }
    public void updateInventoryStationaryItemInStockQty(Map.Entry<String, Integer> entry) {
        stationaryRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())
                .setInStockQuantity(getInventoryStationaryItemInStockQuantity(entry) - OrderedItemAmountRequested(entry));
    }
    public OrderedItem createOrderedStationaryItem(Map.Entry<String, Integer> entry) {
        return orderedItemService
                .save(new OrderedStationaryItem(stationaryRepository
                        .findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())));
    }
    public boolean orderedItemExistsInStationaryRepository(Map.Entry<String, Integer> entry) {
        return stationaryRepository.existsByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey());
    }
    public String requestedStationaryName(Map.Entry<String, Integer> entry) {
        return stationaryRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getName();
    }

    public Integer getInventoryFruitAndVegeItemInStockQuantity(Map.Entry<String, Integer> entry) {
        return fruitAndVegeRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getInStockQuantity();
    }
    public void updateInventoryFruitAndVegeItemInStockQty(Map.Entry<String, Integer> entry) {
        fruitAndVegeRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())
                .setInStockQuantity(getInventoryFruitAndVegeItemInStockQuantity(entry) - OrderedItemAmountRequested(entry));
    }
    public OrderedItem createOrderedFruitAndVegeItem(Map.Entry<String, Integer> entry) {
        return orderedItemService
                .save(new OrderedFruitAndVegeItem(fruitAndVegeRepository
                        .findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())));
    }
    public boolean orderedItemExistsInFruitAndVegeRepository(Map.Entry<String, Integer> entry) {
        return fruitAndVegeRepository.existsByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey());
    }
    public String requestedFruitAndVegeName(Map.Entry<String, Integer> entry) {
        return fruitAndVegeRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getName();
    }

    public Integer getInventoryProcessedFoodItemInStockQuantity(Map.Entry<String, Integer> entry) {
        return processedFoodRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getInStockQuantity();
    }
    public void updateInventoryProcessedFoodItemInStockQty(Map.Entry<String, Integer> entry) {
        processedFoodRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())
                .setInStockQuantity(getInventoryProcessedFoodItemInStockQuantity(entry) - OrderedItemAmountRequested(entry));
    }
    public OrderedItem createOrderedProcessedFoodItem(Map.Entry<String, Integer> entry) {
        return orderedItemService
                .save(new OrderedProcessedFoodItem(processedFoodRepository
                        .findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())));
    }
    public boolean orderedItemExistsInProcessedFoodRepository(Map.Entry<String, Integer> entry) {
        return processedFoodRepository.existsByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey());
    }
    public String requestedProcessedFoodName(Map.Entry<String, Integer> entry) {
        return processedFoodRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getName();
    }*/

}
