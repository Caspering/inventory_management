package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.controllers.PurchaseOrderResourceAssembler;
import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.domain.Items.ProcessedFood;
import com.kasperin.inventory_management.domain.Items.Stationary;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.repository.ItemsRepository.StationaryRepository;
import com.kasperin.inventory_management.services.ResourceNotFoundException;
import com.kasperin.inventory_management.services.commerceServices.PurchaseOrderService;
import com.kasperin.inventory_management.services.itemsServices.StationaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(PurchaseOrderController.BASE_URL)
@RequiredArgsConstructor
public class PurchaseOrderController {

    public static final String BASE_URL = "/api/v1/purchase_orders";

    private final PurchaseOrderService purchaseOrderService;

    private final StationaryService stationaryService;
    private final StationaryRepository stationaryRepository;

    private final PurchaseOrderResourceAssembler assembler;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseOrder> getAll(){
        return purchaseOrderService.findAll();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    EntityModel<PurchaseOrder> getById(@PathVariable Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order with id: " + id+ " was Not Found"));;

        return assembler.toModel(purchaseOrder);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrder save(@RequestBody PurchaseOrder purchaseOrder) {

        return purchaseOrderService.save(purchaseOrder);
    }

    @PatchMapping({"/{id}"})
    public Optional<PurchaseOrder> updatePurchaseOrderDetailsById(@PathVariable Long id, @RequestBody PurchaseOrder purchaseOrder){
        return purchaseOrderService.updatePurchaseOrderDetailsById(id,purchaseOrder);
    }

    @DeleteMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        purchaseOrderService.deleteById(id);
    }


    private void validateItemsExistence(PurchaseOrder items) {
        /*Stationary stationary;

        List<Object> itemsInOrder = new ArrayList<>();

        itemsInOrder.addAll(items.getFruitAndVeges());
        itemsInOrder.addAll(items.getStationaries());
        itemsInOrder.addAll(items.getProcessedFoods());

        itemsInOrder.stream()
                .filter()
        if (itemsInOrder instanceof Stationary){

        }

        itemsInOrder.add(items.)
        List<PurchaseOrder> list = orderProducts
                .stream()
                .filter(stationary -> Objects.isNull(stationaryRepository.get(op
                        .getProduct()
                        .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }*/
    }

}
