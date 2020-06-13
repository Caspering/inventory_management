package com.kasperin.inventory_management.controllers;

import com.kasperin.inventory_management.controllers.v1.PurchaseOrderController;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PurchaseOrderResourceAssembler
        implements RepresentationModelAssembler<PurchaseOrder, EntityModel<PurchaseOrder>> {


    @Override
    public EntityModel<PurchaseOrder> toModel(PurchaseOrder entity) {
        return new EntityModel<PurchaseOrder>(entity,
                linkTo(PurchaseOrderController.class).slash(entity.getId()).withSelfRel(),
                linkTo(PurchaseOrderController.class).withRel("purchase_orders"));
    }

    @Override
    public CollectionModel<EntityModel<PurchaseOrder>> toCollectionModel(Iterable<? extends PurchaseOrder> entities) {
        return null;
    }
}
