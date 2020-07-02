package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.api.v1.model.PurchaseOrderItemDto;
import com.kasperin.inventory_management.controllers.PurchaseOrderResourceAssembler;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.controllers.exceptions.ResourceNotFoundException;
import com.kasperin.inventory_management.domain.enums.FoodType;
import com.kasperin.inventory_management.services.commerceServices.PurchaseOrderService;
import com.kasperin.inventory_management.services.commerceServices.PurchaseOrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(PurchaseOrderController.BASE_URL)
@RequiredArgsConstructor
public class PurchaseOrderController {

    public static final String BASE_URL = "/api/v1/purchase_orders";
    private final PurchaseOrderService purchaseOrderService;
    private final PurchaseOrderResourceAssembler assembler;

    //@GetMapping({"/{id}"})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<PurchaseOrder> getByReceiptNumber(@RequestParam(value = "any", defaultValue = "") String input) {
        return purchaseOrderService.findAllByReceiptNumberContaining(input);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseOrder> getAll() {
            return purchaseOrderService.findAll();

    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    EntityModel<PurchaseOrder> getById(@PathVariable Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order with id: " + id+ " was Not Found"));
       return assembler.toModel(purchaseOrder);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrder save(@RequestBody PurchaseOrderItemDto form, BindingResult result, Model model){
        if (result.hasErrors()) {
           result.getFieldErrors();
            model.addAttribute("message", "The information is invalid!");
        }

        return purchaseOrderService.save(form);
    }

    @PatchMapping({"/{id}"})
    public Optional<PurchaseOrder> updatePurchaseOrderDetailsById(@PathVariable Long id,
                                                                  @RequestBody PurchaseOrder purchaseOrder){
        return purchaseOrderService.updatePurchaseOrderDetailsById(id,purchaseOrder);
    }
    @DeleteMapping({"{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        purchaseOrderService.deleteById(id);
    }
}
