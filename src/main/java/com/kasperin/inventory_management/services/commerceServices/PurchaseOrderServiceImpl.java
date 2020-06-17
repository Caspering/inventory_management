package com.kasperin.inventory_management.services.commerceServices;

import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.domain.Items.Item;
import com.kasperin.inventory_management.domain.Items.ProcessedFood;
import com.kasperin.inventory_management.domain.Items.Stationary;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.repository.ItemsRepository.FruitAndVegeRepository;
import com.kasperin.inventory_management.repository.ItemsRepository.ItemRepository;
import com.kasperin.inventory_management.repository.ItemsRepository.ProcessedFoodRepo;
import com.kasperin.inventory_management.repository.ItemsRepository.StationaryRepository;
import com.kasperin.inventory_management.repository.commerceRepository.PurchaseOrderRepository;
import com.kasperin.inventory_management.repository.customerRepository.MemberRepository;
import com.kasperin.inventory_management.services.ResourceNotFoundException;
import com.kasperin.inventory_management.services.itemsServices.UpdateProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PrePersist;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService  {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final FruitAndVegeRepository fruitAndVegeRepository;
    private final StationaryRepository stationaryRepository;
    private final ProcessedFoodRepo processedFoodRepo;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

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
            throw new ResourceNotFoundException("No Purchase order was created on the date: " + createdDate);
        }
    }

    @Override
    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public List<PurchaseOrder> findAllByCreatedDate(LocalDate createdDate) {
        return getAllPurchaseOrderCreatedOn(createdDate);
    }

    @Override
    public List<PurchaseOrder> findAllByDateCreatedBetween(LocalDate startDate, LocalDate endDate) {
        return purchaseOrderRepository.findAllByDateCreatedBetween(startDate, endDate);
    }

    @Override
    public PurchaseOrder findByReceiptNumberIgnoreCase(String receiptNumber) {
        return getPurchaseOrderByReceiptNumberIgnoreCase(receiptNumber);
    }

    @Override
    public List<PurchaseOrder> findAllByMemberNumber(String memberNumber) {
        return purchaseOrderRepository
                .findAllByMemberNumberIgnoreCase(memberNumber);
    }

    @Override
    public Optional<PurchaseOrder> findById(Long id) {
       return getPurchaseOrderById(id);
    }


    @Override
    public PurchaseOrder save(@Valid PurchaseOrder purchaseOrder) {
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
}
