package com.kasperin.inventory_management.repository.commerceRepository;

import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    PurchaseOrder findByPurchaseOrderNumberIgnoreCase(String purchaseOrderNumber);

    List<PurchaseOrder> findAllByDateCreated(LocalDate dateCreated);

    List<PurchaseOrder> findAllByMemberNumberIgnoreCase(String memberNumber);

}
