package com.kasperin.inventory_management.services.commerceServices;

import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.repository.commerceRepository.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public List<PurchaseOrder> findAllByCreatedDate(LocalDate createdDate) {
        return purchaseOrderRepository.findAllByDateCreated(createdDate);
    }

    @Override
    public List<PurchaseOrder> findAllByDateCreatedBetween(LocalDate startDate, LocalDate endDate) {
        return purchaseOrderRepository.findAllByDateCreatedBetween(startDate, endDate);
    }

    @Override
    public Optional<PurchaseOrder> findByReceiptNumberIgnoreCase(String receiptNumber) {
        return purchaseOrderRepository
                .findByReceiptNumberIgnoreCase(receiptNumber);
    }

    @Override
    public List<PurchaseOrder> findAllByMemberNumber(String memberNumber) {
        return purchaseOrderRepository
                .findAllByMemberNumberIgnoreCase(memberNumber);
    }

    @Override
    public Optional<PurchaseOrder> findById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    @Override
    public PurchaseOrder save(@Valid PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public Optional<PurchaseOrder> updateById(Long id, @Valid PurchaseOrder newPurchaseOrder) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        purchaseOrderRepository.deleteById(id);
    }

    @Override
    public boolean existsById(PurchaseOrder purchaseOrder) {
        return existsById(purchaseOrder);
    }
}
