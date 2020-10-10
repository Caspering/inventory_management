package com.kasperin.inventory_management.repository.commerceRepository;

import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    boolean existsByReceiptNumberIgnoreCase(String name);
    boolean existsByDateCreated(LocalDate createdDate);
    boolean existsByMemberNumber(String memberNumber);
    boolean existsByMemberNumberNull();

    Optional<PurchaseOrder> findById(Long id);
    PurchaseOrder findByReceiptNumberIgnoreCase(String purchaseOrderReceiptNumber);

    List<PurchaseOrder> findAllPurchaseOrderByMemberNumberContaining(String memberNumber);
    List<PurchaseOrder> findAllByReceiptNumberContaining(String recieptNumber);
    List<PurchaseOrder> findAllByDateCreated(LocalDate dateCreated);
    List<PurchaseOrder> findAllByDateCreatedBetween(LocalDate startDate,
                                                    LocalDate endDate);
    List<PurchaseOrder> findAllByMemberNumberIgnoreCase(String memberNumber);
    List<PurchaseOrder> findAllByMemberNumberIsNull();



}
