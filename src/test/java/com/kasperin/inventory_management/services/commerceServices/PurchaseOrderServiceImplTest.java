package com.kasperin.inventory_management.services.commerceServices;

import com.kasperin.inventory_management.controllers.exceptions.ResourceNotFoundException;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.repository.commerceRepository.PurchaseOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PurchaseOrderServiceImplTest {

    @Mock
    PurchaseOrderRepository purchaseOrderRepository;

    @InjectMocks
    PurchaseOrderServiceImpl purchaseOrderServiceImpl;

    @Captor
    ArgumentCaptor<Long> longArgumentCaptor;

    @BeforeEach
    void setUp() {

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(1L);
        purchaseOrderRepository.save(purchaseOrder);
//        given(purchaseOrderServiceImpl.findById(1L));
//        when(purchaseOrderServiceImpl.existsById(longArgumentCaptor.capture())).thenReturn(true);
//
//        given(purchaseOrderServiceImpl.existsById(longArgumentCaptor.capture()));
//        when(purchaseOrderServiceImpl.findById(1L)).thenReturn(purchaseOrderRepository.findById(1L));

       /* given(purchaseOrderServiceImpl.existsById(longArgumentCaptor.capture()))
                .willAnswer(invocation -> {

                    Long id = invocation.getArgument(0);
                    if (id.equals(purchaseOrderRepository.findById(1L)))
                    return true;
                    else return false;
                        //throw new ResourceNotFoundException("Not found in test");

//                    List<Owner> owners = new ArrayList<>();
//
//                    String name = invocation.getArgument(0);
//
//                    if (name.equals("%Buck%")) {
//                        owners.add(new Owner(1l, "Joe", "Buck"));
//                        return owners;
//                    } else if (name.equals("%DontFindMe%")) {
//                        return owners;
//                    } else if (name.equals("%FindMe%")) {
//                        owners.add(new Owner(1l, "Joe", "Buck"));
//                        owners.add(new Owner(2l, "Joe2", "Buck2"));
//                        return owners;
//                    }
//
//                    throw new RuntimeException("Invalid Argument");
                });*/
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {


        //given - none
        //.willAnswer(purchaseOrderRepository.findById(1l));
        //given(purchaseOrderRepository.existsById(1L)).willReturn(true);
        //when
        purchaseOrderServiceImpl.deleteById(1L);


       // when(purchaseOrderRepository.findById(1L)).thenReturn(purchaseOrderRepository.findById(1L));

        //then
        then(purchaseOrderRepository).should(times(1)).deleteById(1L);


    }
}