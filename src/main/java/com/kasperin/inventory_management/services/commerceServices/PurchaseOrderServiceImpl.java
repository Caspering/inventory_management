package com.kasperin.inventory_management.services.commerceServices;

import com.kasperin.inventory_management.controllers.v1.Dto.OrderAnalysisDto;
import com.kasperin.inventory_management.controllers.v1.Dto.PurchaseOrderItemDto;
import com.kasperin.inventory_management.domain.Items.OrderedFruitAndVegeItem;
import com.kasperin.inventory_management.domain.Items.OrderedItem;
import com.kasperin.inventory_management.domain.Items.OrderedProcessedFoodItem;
import com.kasperin.inventory_management.domain.Items.OrderedStationaryItem;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.repository.ItemsRepository.FruitAndVegeRepository;
import com.kasperin.inventory_management.repository.ItemsRepository.OrderedItemRepository;
import com.kasperin.inventory_management.repository.ItemsRepository.ProcessedFoodRepo;
import com.kasperin.inventory_management.repository.ItemsRepository.StationaryRepository;
import com.kasperin.inventory_management.repository.commerceRepository.PurchaseOrderRepository;
import com.kasperin.inventory_management.repository.customerRepository.MemberRepository;
import com.kasperin.inventory_management.services.ResourceNotFoundException;
import com.kasperin.inventory_management.services.itemsServices.OrderedItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService  {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final FruitAndVegeRepository fruitAndVegeRepository;
    private final StationaryRepository stationaryRepository;
    private final OrderedItemRepository orderedItemService;
    private final ProcessedFoodRepo processedFoodRepository;
    private final MemberRepository memberRepository;
    //private final OrderAnalysisDto orderAnalysisDto;

    private Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        if (purchaseOrderRepository.existsById(id)) {
            return purchaseOrderRepository.findById(id);//.orElseThrow(() -> new ResourceNotFoundException("wasnt found"));
        }else{
            throw new ResourceNotFoundException
                    ("Purchase order with the requested id: "+ id +" was not found");
        }
    }
    private PurchaseOrder getPurchaseOrderByReceiptNumberIgnoreCase(String purchaseOrderRecieptNumber) {
        if (purchaseOrderRepository.existsByReceiptNumberIgnoreCase(purchaseOrderRecieptNumber)) {
            return purchaseOrderRepository.findByReceiptNumberIgnoreCase(purchaseOrderRecieptNumber);
        }else{
            throw new ResourceNotFoundException("The PurchaseOrder item with receipt number: "
                    + purchaseOrderRecieptNumber + " does not exist");
        }
    }
    private List<PurchaseOrder> getAllPurchaseOrderCreatedOn(LocalDate createdDate){
        if (purchaseOrderRepository.existsByDateCreated(createdDate)){
            return purchaseOrderRepository.findAllByDateCreated(createdDate);
        }else{
            throw new ResourceNotFoundException
                    ("No Purchase order was created on the date: " + createdDate);
        }
    }
    private List<PurchaseOrder> getAllPurchaseOrderCreatedBetween(LocalDate startDate, LocalDate endDate){
        if (purchaseOrderRepository.existsByDateCreated(startDate)&&purchaseOrderRepository.existsByDateCreated(endDate)){
            return purchaseOrderRepository.findAllByDateCreatedBetween(startDate,endDate);
        }else{
            throw new ResourceNotFoundException
                    ("No Purchase order was created between the date: " +startDate+" and "+endDate);
        }
    }
    private List<PurchaseOrder> getAllPurchaseOrderByMemberNumbers(String memberNumber){
        if(purchaseOrderRepository.existsByMemberNumber(memberNumber)){
            return purchaseOrderRepository.findAllByMemberNumberIgnoreCase(memberNumber);
        }else{
            throw new ResourceNotFoundException("No Purchase order contains the member number: "+memberNumber);
        }
    }
    private List<PurchaseOrder> getAllPurchaseOrderWithNullMemberNumber(){
        if(purchaseOrderRepository.existsByMemberNumberNull()){
            return purchaseOrderRepository.findAllByMemberNumberIsNull();
        }else{
            throw new ResourceNotFoundException("No Purchase order exists with no member number");
        }
    }

    @Override
    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public Optional<PurchaseOrder> findById(Long id) {
        return getPurchaseOrderById(id);
    }

    @Override
    public List<PurchaseOrder> findAllByCreatedDate(LocalDate createdDate) {
        return getAllPurchaseOrderCreatedOn(createdDate);
    }

    /*@Override
    public */

    @Override
    public List<PurchaseOrder> findAllByDateCreatedBetween(LocalDate startDate, LocalDate endDate) {
        return getAllPurchaseOrderCreatedBetween(startDate, endDate);
    }

    @Override
    public PurchaseOrder findByReceiptNumberIgnoreCase(String receiptNumber) {
        return getPurchaseOrderByReceiptNumberIgnoreCase(receiptNumber);
    }

    @Override
    public List<PurchaseOrder> findAllByMemberNumber(String memberNumber) {
        return getAllPurchaseOrderByMemberNumbers(memberNumber);
    }

    @Override
    public List<PurchaseOrder> findAllWithNoMemberNumber(){
        return getAllPurchaseOrderWithNullMemberNumber();
    }




    @Override
    @Transactional
    public PurchaseOrder save(@Valid OrderForm form) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
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
        }else throw new RuntimeException("Purchase order must contain an item");
        return purchaseOrderRepository.save(purchaseOrder);
    }


    @Override
    public void deleteById(Long id)  {
       // purchaseOrderRepository.delete(findById(id));
        getPurchaseOrderById(id).map(purchaseOrder -> {purchaseOrderRepository.delete(purchaseOrder);
        return null;
        });
    }

    @Override
    public boolean existsById(Long id) {
        return purchaseOrderRepository.existsById(id);
    }

    @Override
    public Optional<PurchaseOrder> removePurchaseOrderItemByIdAndReceiptNumber(Long id, Object item) {
        return Optional.empty();
    }

    @Override
    public Optional<PurchaseOrder> addPurchaseOrderItemByIdAndReceiptNumber(Long id, String receiptNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<PurchaseOrder> updatePurchaseOrderDetailsById(Long id, @Valid PurchaseOrder purchaseOrderPatch) {
        return getPurchaseOrderById(id)
                .map(purchaseOrderInDB -> {

                    /*if(purchaseOrderPatch.getTotalPurchaseOrderPrice() >= 0) {
                        purchaseOrderInDB.ssetInStockQuantity(purchaseOrderPatch.getInStockQuantity());*/

                    if (purchaseOrderPatch.getMember() != null)
                        purchaseOrderInDB.setMember(purchaseOrderPatch.getMember());

                    if (purchaseOrderPatch.getDateCreated() != null)
                        purchaseOrderInDB.setDateCreated(purchaseOrderPatch.getDateCreated());

                    if (purchaseOrderPatch.getMember() != null)
                        purchaseOrderInDB.setMember(purchaseOrderPatch.getMember());

                    if (purchaseOrderPatch.getTotalNumberOfItems() != null)
                        purchaseOrderInDB.setTotalNumberOfItems(
                                purchaseOrderPatch.getTotalNumberOfItems());

                    if (purchaseOrderPatch.getReceiptNumber() != null)
                        purchaseOrderInDB.setReceiptNumber(
                                purchaseOrderPatch.getReceiptNumber());

                    if (purchaseOrderPatch.getPaymentType() != null)
                        purchaseOrderInDB.setPaymentType(purchaseOrderPatch.getPaymentType());

                    log.info("The purchase order with id: "
                            + purchaseOrderInDB.getId() + ", and receipt number: '"
                            + purchaseOrderInDB.getReceiptNumber() + "' was updated.");

                    return purchaseOrderRepository.save(purchaseOrderInDB);

                    //return purchaseOrderInDB;
                });
    }





    //Helper Methods for building a purchase order

    public static class OrderForm{

        private PurchaseOrderItemDto purchaseOrderItemDto;

        public void setPurchaseOrderItemDto(PurchaseOrderItemDto p){
            this.purchaseOrderItemDto = p;
        }

        public PurchaseOrderItemDto getPurchaseOrderItemDto(){
            return purchaseOrderItemDto;
        }
    }

    public boolean itemListIsEmptyOrNull(@RequestBody OrderForm form) {
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
    }
}
