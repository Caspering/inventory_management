package com.kasperin.inventory_management.services.commerceServices;

import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.repository.ItemsRepository.FruitAndVegeRepository;
import com.kasperin.inventory_management.repository.commerceRepository.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final FruitAndVegeRepository fruitAndVegeRepository;


    @Override
   // @Transactional(readOnly = true)
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
        /*return  purchaseOrder.getFruitAndVeges()
                .stream()
                .map(fruitAndVege -> {
                    if (fruitAndVege.getInStockQuantity() == 0) {//make sure he is ordering more than one
                        throw new RuntimeException("in stock quantity has to be greater than 0");
                    }
                    fruitAndVegeRepository.findById(fruitAndVege.getId())
                            .map(fruitAndVegeInDB -> {
                                if(fruitAndVegeInDB.getInStockQuantity() > 0
                                        && fruitAndVegeInDB.getInStockQuantity() >
                                        fruitAndVege.getInStockQuantity()) {
                                    fruitAndVegeInDB.setInStockQuantity(fruitAndVegeInDB.getInStockQuantity()
                                            - fruitAndVege.getInStockQuantity());
                                    fruitAndVegeRepository.save(fruitAndVegeInDB);
                                    purchaseOrder.setFruitAndVeges(purchaseOrder.getFruitAndVeges());

                                    return purchaseOrderRepository.save(purchaseOrder);
                                }
                                else if(fruitAndVege.getInStockQuantity() > fruitAndVegeInDB.getInStockQuantity()){
                                    throw new RuntimeException("you are ordering more than we have in stock");
                                }
                                else if (fruitAndVegeInDB.getInStockQuantity() == 0 )
                                    throw new RuntimeException("no fruit and vege in stock");
                                return null;
                            });
                    purchaseOrderRepository.save(purchaseOrder);
                    return purchaseOrder;
                });*/
       //purchaseOrder.setStationaries(purchaseOrder.getStationaries());
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






    //       Set<FruitAndVege> orderedFruitAndVeges = new HashSet<>();
//
/*                purchaseOrder.getFruitAndVeges()
                        .stream()
                        .map(fruitAndVege -> fruitAndVegeRepository
                                .findById(fruitAndVege.getId())
                                .save(fruitAndVege.setInStockQuantity());
                .collect(Collectors.toSet());

        List<Optional<FruitAndVege>> favDb = new ArrayList<>();

        for(FruitAndVege fav : purchaseOrder.getFruitAndVeges()){
            orderedFruitAndVeges.add(fav);
            favDb.add(fruitAndVegeRepository.findById(fav.getId()));
        }

        purchaseOrder.setFruitAndVeges(orderedFruitAndVeges);

        favDb.stream()
                .map(fruitAndVege -> fruitAndVegeRepository.save(fruitAndVege.);*/


       /* //purchaseOrder.setFruitAndVeges(orderedFruitAndVeges);

        for(FruitAndVege fav : orderedFruitAndVeges){
            Optional<FruitAndVege> fav1 = fruitAndVegeRepository.findById(fav.getId());
            Objects.requireNonNull(fav1, "You can not buy a non existing product");
            fav1.stream()
                    .filter(fav1 -> fav1.getInStockQuantity() - fav.getInStockQuantity());
           // purchaseOrder.add
            //fav.setInStockQuantity(fav.getInStockQuantity()-purchaseOrder.get);
        }
        purchaseOrder.setFruitAndVeges(orderedFruitAndVeges);*/
    //return purchaseOrderRepository.save(purchaseOrder);
}
