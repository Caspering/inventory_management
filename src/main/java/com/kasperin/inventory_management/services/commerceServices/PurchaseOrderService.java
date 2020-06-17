package com.kasperin.inventory_management.services.commerceServices;

import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface PurchaseOrderService {

    List<PurchaseOrder> findAll();

    List<PurchaseOrder> findAllByCreatedDate(LocalDate createdDate);

    List<PurchaseOrder> findAllByDateCreatedBetween(LocalDate startDate,
                                                    LocalDate endDate);

    PurchaseOrder findByReceiptNumberIgnoreCase(String receiptNumber);

    List<PurchaseOrder> findAllByMemberNumber(String memberNumber);

    Optional<PurchaseOrder> findById(Long id);

    PurchaseOrder save(@Valid PurchaseOrder purchaseOrder);

    Optional<PurchaseOrder> removePurchaseOrderItemByIdAndReceiptNumber(Long id, Object item);

    Optional<PurchaseOrder> addPurchaseOrderItemByIdAndReceiptNumber(Long id, String receiptNumber);

    Optional<PurchaseOrder> updatePurchaseOrderDetailsById(Long id, @Valid PurchaseOrder purchaseOrderPatch);

    void deleteById(Long id);

    //boolean existsById(PurchaseOrder purchaseOrder);

    boolean existsById(Long id);
}