package com.kasperin.inventory_management.services.commerceServices;

import com.kasperin.inventory_management.domain.Items.Stationary;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface PurchaseOrderService {

    List<PurchaseOrder> findAll();

    List<PurchaseOrder> findAllByCreatedDate(LocalDate createdDate);

    List<PurchaseOrder> findAllByDateCreatedBetween(LocalDate startDate,
                                                    LocalDate endDate);

    Optional<PurchaseOrder> findByReceiptNumberIgnoreCase(String receiptNumber);

    List<PurchaseOrder> findAllByMemberNumber(String memberNumber);

    Optional<PurchaseOrder> findById(Long id);

    PurchaseOrder save(@Valid PurchaseOrder purchaseOrder);

    Optional<PurchaseOrder> removePurchaseOrderItemByIdAndReceiptNumber(Long id, String receiptNumber);

    Optional<PurchaseOrder> addPurchaseOrderItemByIdAndReceiptNumber(Long id, String receiptNumber);

    Optional<PurchaseOrder> updatePurchaseOrderDetailsById(Long id, @Valid PurchaseOrder purchaseOrderPatch);

    void deleteById(Long id);

    boolean existsById(PurchaseOrder purchaseOrder);

}
