package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.domain.customer.Member;
import com.kasperin.inventory_management.repository.commerceRepository.PurchaseOrderRepository;
import com.kasperin.inventory_management.services.commerceServices.PurchaseOrderService;
import com.kasperin.inventory_management.services.commerceServices.PurchaseOrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PurchaseOrderControllerTest {

    public static final long ID = 1L;

    @Mock
    PurchaseOrderServiceImpl service;

    @Mock
    PurchaseOrderRepository purchaseOrderRepository;

    @InjectMocks
    PurchaseOrderController controller;

    @BeforeEach
    void setUp() {
    }

//    @Test  //change for get any
//    void getAll() {
//        //given
//        PurchaseOrder purchaseOrder = new PurchaseOrder();
//        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
//        purchaseOrders.add(purchaseOrder);
//        given(service.findAll()).willReturn(purchaseOrders);
//
//        //when
//        List<PurchaseOrder> foundPurchaseOrders = controller.getAll();
//
//        //then
//        then(service).should().findAll();
//        assertThat(foundPurchaseOrders).hasSize(1);
//    }

    @Test
    void getById() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }
}