package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.domain.Items.Stationary;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.services.commerceServices.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PurchaseOrderController.BASE_URL)
@RequiredArgsConstructor
public class PurchaseOrderController {

    public static final String BASE_URL = "/api/v1/purchase_orders";

    private final PurchaseOrderService purchaseOrderService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseOrder> getAll(){
        return purchaseOrderService.findAll();
    }

/*    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    Resource<PurchaseOrder> getById(@PathVariable Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderService.findById(id);

        return new Resource<>(purchaseOrder,
                linkTo(methodOn(PurchaseOrderController.class).one(id)).withSelRel(),
                linkTo(methodOn(PurchaseOrderController.class).all()).withRel("purchase_orders"));
    }*/

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


}
