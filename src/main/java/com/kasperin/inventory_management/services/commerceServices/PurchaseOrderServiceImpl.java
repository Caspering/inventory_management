package com.kasperin.inventory_management.services.commerceServices;

import com.kasperin.inventory_management.api.v1.model.AssociationDto;
import com.kasperin.inventory_management.api.v1.model.PurchaseOrderItemDto;
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
import com.kasperin.inventory_management.services.RecommendationService;
import com.kasperin.inventory_management.controllers.exceptions.ResourceNotFoundException;
import com.kasperin.inventory_management.services.customerServices.MemberService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
    private final RecommendationService recommendationService;
    private final MemberService memberService;


    private Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        if (purchaseOrderRepository.existsById(id)) {
            return purchaseOrderRepository.findById(id);//.orElseThrow(() -> new ResourceNotFoundException("wasnt found"));
        }else throw new ResourceNotFoundException
                ("Purchase order with the requested id: " + id + " was not found");
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
        if (purchaseOrderRepository.existsByDateCreated(startDate)||purchaseOrderRepository.existsByDateCreated(endDate))
            return purchaseOrderRepository.findAllByDateCreatedBetween(startDate,endDate);
            throw new ResourceNotFoundException
                    ("No Purchase order was created between the date: " +startDate+" and "+endDate);
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

    @Override
    public List<PurchaseOrder> findAllByDateCreatedBetween(LocalDate startDate, LocalDate endDate) {
        return getAllPurchaseOrderCreatedBetween(startDate, endDate);
    }

    @Override
    public PurchaseOrder findByReceiptNumberIgnoreCase(String receiptNumber) {
        return getPurchaseOrderByReceiptNumberIgnoreCase(receiptNumber);
    }

    @Override
    public List<PurchaseOrder> findAllByReceiptNumberContaining(String recieptNumber) {
        return purchaseOrderRepository.findAllByReceiptNumberContaining(recieptNumber);
    }

    @Override
    public List<PurchaseOrder> findAllPurchaseOrderByMemberNumberContaining(String memberNumber) {
        return findAllPurchaseOrderByMemberNumberContaining(memberNumber);
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
    public PurchaseOrder save(PurchaseOrderItemDto form) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        if(form.getPaymentType() != null)
            purchaseOrder.setPaymentType(form.getPaymentType());

        if(form.getMemberNumber() != null){
            purchaseOrder.setMemberNumber(form.getMemberNumber());
            purchaseOrder.setMember(memberService.findByMemberNumber(form.getMemberNumber()));
        }

        if (itemListIsEmptyOrNull(form)) throw new RuntimeException("Purchase order must contain an item");
        form.getItems().entrySet().forEach(entry -> saveOrderedItem(purchaseOrder, entry));

        if(form.getDiscountStrategy() != null)
            purchaseOrder.setDiscountAmount(purchaseOrder.getTotalPrice() * form.getDiscountStrategy().discountRate);

        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public void deleteById(Long id)  {
//       // purchaseOrderRepository.delete(findById(id));
        getPurchaseOrderById(id).map(purchaseOrder -> {purchaseOrderRepository.delete(purchaseOrder);
        return null;
        });

//        if(purchaseOrderRepository.findById(id).isEmpty()){
//            throw new ResourceNotFoundException("Purchase order with the requested id: " + id + " was not found");
//        }
//        purchaseOrderRepository.deleteById(id);
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

    private boolean itemListIsEmptyOrNull(@RequestBody PurchaseOrderItemDto form) {
        return org.springframework.util.ObjectUtils.isEmpty(form.getItems());
    }

    private void saveOrderedItem(PurchaseOrder purchaseOrder, Map.Entry<String, Integer> entry) {
        if (orderedItemExistsInStationaryRepository(entry)) saveStationaryOrderedIten(purchaseOrder, entry);
        if (orderedItemExistsInFruitAndVegeRepository(entry)) saveFruitAndVegeOrderedItem(purchaseOrder, entry);
        if (orderedItemExistsInProcessedFoodRepository(entry)) saveProcessedFoodOrderedItem(purchaseOrder, entry);
        throw new RuntimeException("Item "+entry.getKey()+" was not found in inventory");
    }
    private void saveStationaryOrderedIten(PurchaseOrder purchaseOrder, Map.Entry<String, Integer> entry) {
        if(requestedStationaryAmountIsAvailable(entry)) {
            if (requestedStationaryAmountIsEqualToOne(entry)) {
                OrderedItem orderedItem = createOrderedStationaryItem(entry);
                setOrderedItemAmount(entry, orderedItem);
                updateInventoryStationaryItemInStockQty(entry);
                purchaseOrder.addItem(orderedItem);
            }
            throw new RuntimeException("Restriction: Requested Stationary amount for "
                    + requestedStationaryName(entry)
                    +" is restricted to one. Stationary is limited to one quantity per item type per customer purchase order");
        }
        throw new RuntimeException("Requested Stationary item amount for: "
                + requestedStationaryName(entry)
                +" is not available. Available amount is "
                +getInventoryStationaryItemInStockQuantity(entry)+".");
    }
    private void saveFruitAndVegeOrderedItem(PurchaseOrder purchaseOrder, Map.Entry<String, Integer> entry) {
        if (!requestedFruitAndVegeAmountIsAvailable(entry))
            throw new RuntimeException("Requested FruitAndVege item amount for: "
                    + requestedFruitAndVegeName(entry)
                    + " is not available. Available amount is "
                    + getInventoryFruitAndVegeItemInStockQuantity(entry) + ".");
        else {
            if (requestedFruitAndVegeAmountIsGreaterThanZero(entry)) {
                OrderedItem orderedItem = createOrderedFruitAndVegeItem(entry);
                setOrderedItemAmount(entry, orderedItem);
                updateInventoryFruitAndVegeItemInStockQty(entry);
                purchaseOrder.addItem(orderedItem);
                recommendationService.createAssociation(new AssociationDto(orderedItem.getBarcode(),
                        orderedItem.getBarcode()));
            }
            throw new RuntimeException("Requested FruitAndVege amount for: "
                    + requestedFruitAndVegeName(entry)
                    + " have to be greater than zero");
        }
    }
    private void saveProcessedFoodOrderedItem(PurchaseOrder purchaseOrder, Map.Entry<String, Integer> entry) {
        if(requestedProcessedFoodAmountIsAvailable(entry)) {
            if (requestedProcessedFoodAmountIsGreaterThanZero(entry)) {
                OrderedItem orderedItem = createOrderedProcessedFoodItem(entry);
                setOrderedItemAmount(entry, orderedItem);
                updateInventoryProcessedFoodItemInStockQty(entry);
                purchaseOrder.addItem(orderedItem);
            }else throw new RuntimeException("Requested ProcessedFood amount for: "
                    + requestedProcessedFoodName(entry)
                    +" have to be greater than zero.");
        }else throw new RuntimeException("Requested ProcessedFood item amount for "
                + requestedProcessedFoodName(entry)
                +" is not available. Available amount is "
                +getInventoryProcessedFoodItemInStockQuantity(entry)+".");
    }

    private boolean requestedStationaryAmountIsEqualToOne(Map.Entry<String, Integer> entry) {
        return OrderedItemAmountRequested(entry)==1;
    }
    private boolean requestedStationaryAmountIsAvailable(Map.Entry<String, Integer> entry) {
        return getInventoryStationaryItemInStockQuantity(entry) >= OrderedItemAmountRequested(entry);
    }

    private boolean requestedFruitAndVegeAmountIsGreaterThanZero(Map.Entry<String, Integer> entry) {
        return OrderedItemAmountRequested(entry)>0;
    }
    private boolean requestedFruitAndVegeAmountIsAvailable(Map.Entry<String, Integer> entry) {
        return getInventoryFruitAndVegeItemInStockQuantity(entry) >= OrderedItemAmountRequested(entry);
    }

    private boolean requestedProcessedFoodAmountIsGreaterThanZero(Map.Entry<String, Integer> entry) {
        return OrderedItemAmountRequested(entry)>0;
    }
    private boolean requestedProcessedFoodAmountIsAvailable(Map.Entry<String, Integer> entry) {
        return getInventoryProcessedFoodItemInStockQuantity(entry) >= OrderedItemAmountRequested(entry);
    }

    private Integer OrderedItemAmountRequested(Map.Entry<String, Integer> entry) {
        return entry.getValue();
    }
    private void setOrderedItemAmount(Map.Entry<String, Integer> entry, OrderedItem orderedItem) {
        orderedItem.setQuantity(OrderedItemAmountRequested(entry));
    }

    private Integer getInventoryStationaryItemInStockQuantity(Map.Entry<String, Integer> entry) {
        return stationaryRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getInStockQuantity();
    }
    private void updateInventoryStationaryItemInStockQty(Map.Entry<String, Integer> entry) {
        stationaryRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())
                .setInStockQuantity(getInventoryStationaryItemInStockQuantity(entry) - OrderedItemAmountRequested(entry));
    }
    private OrderedItem createOrderedStationaryItem(Map.Entry<String, Integer> entry) {
        return orderedItemService
                .save(new OrderedStationaryItem(stationaryRepository
                        .findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())));
    }
    private boolean orderedItemExistsInStationaryRepository(Map.Entry<String, Integer> entry) {
        return stationaryRepository.existsByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey());
    }
    private String requestedStationaryName(Map.Entry<String, Integer> entry) {
        return stationaryRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getName();
    }

    private Integer getInventoryFruitAndVegeItemInStockQuantity(Map.Entry<String, Integer> entry) {
        return fruitAndVegeRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getInStockQuantity();
    }
    private void updateInventoryFruitAndVegeItemInStockQty(Map.Entry<String, Integer> entry) {
        fruitAndVegeRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())
                .setInStockQuantity(getInventoryFruitAndVegeItemInStockQuantity(entry) - OrderedItemAmountRequested(entry));
    }
    private OrderedItem createOrderedFruitAndVegeItem(Map.Entry<String, Integer> entry) {
        return orderedItemService
                .save(new OrderedFruitAndVegeItem(fruitAndVegeRepository
                        .findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())));
    }
    private boolean orderedItemExistsInFruitAndVegeRepository(Map.Entry<String, Integer> entry) {
        return fruitAndVegeRepository.existsByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey());
    }
    private String requestedFruitAndVegeName(Map.Entry<String, Integer> entry) {
        return fruitAndVegeRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getName();
    }

    private Integer getInventoryProcessedFoodItemInStockQuantity(Map.Entry<String, Integer> entry) {
        return processedFoodRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getInStockQuantity();
    }
    private void updateInventoryProcessedFoodItemInStockQty(Map.Entry<String, Integer> entry) {
        processedFoodRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())
                .setInStockQuantity(getInventoryProcessedFoodItemInStockQuantity(entry) - OrderedItemAmountRequested(entry));
    }
    private OrderedItem createOrderedProcessedFoodItem(Map.Entry<String, Integer> entry) {
        return orderedItemService
                .save(new OrderedProcessedFoodItem(processedFoodRepository
                        .findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey())));
    }
    private boolean orderedItemExistsInProcessedFoodRepository(Map.Entry<String, Integer> entry) {
        return processedFoodRepository.existsByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey());
    }
    private String requestedProcessedFoodName(Map.Entry<String, Integer> entry) {
        return processedFoodRepository.findByBarcodeOrNameIgnoreCase(entry.getKey(), entry.getKey()).getName();
    }
}
