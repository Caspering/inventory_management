package com.kasperin.inventory_management.repository.commerceRepository;

import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    Optional<PurchaseOrder> findByReceiptNumberIgnoreCase(String purchaseOrderNumber);

    List<PurchaseOrder> findAllByDateCreated(LocalDate dateCreated);

    List<PurchaseOrder> findAllByDateCreatedBetween(LocalDate startDate,
                                                    LocalDate endDate);

    List<PurchaseOrder> findAllByMemberNumberIgnoreCase(String memberNumber);

}
