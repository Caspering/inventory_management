package com.kasperin.inventory_management.services.commerceServices;

import com.kasperin.inventory_management.api.v1.model.PurchaseOrderItemDto;
import com.kasperin.inventory_management.controllers.exceptions.ResourceNotFoundException;
import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.domain.Items.OrderedItem;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.domain.customer.Member;
import com.kasperin.inventory_management.repository.ItemsRepository.FruitAndVegeRepository;
import com.kasperin.inventory_management.repository.ItemsRepository.OrderedItemRepository;
import com.kasperin.inventory_management.repository.ItemsRepository.StationaryRepository;
import com.kasperin.inventory_management.repository.commerceRepository.PurchaseOrderRepository;
import org.hibernate.criterion.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class PurchaseOrderServiceImplTest {

    public static final long ID = 1L;

    @Mock
    PurchaseOrderRepository purchaseOrderRepository;

    @Mock
    StationaryRepository stationaryRepository;

    @Mock
    FruitAndVegeRepository fruitAndVegeRepository;

    @Mock
    OrderedItemRepository orderedItemRepository;

    @InjectMocks
    PurchaseOrderServiceImpl purchaseOrderServiceImpl;

    @Captor
    ArgumentCaptor<Map<String, Integer>> mapGetItemsArgumentCaptor;

    @Captor
    ArgumentCaptor<Integer> integerArgumentCaptor;


    @Test
    void findAll() {
        //given
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        purchaseOrders.add(purchaseOrder);
        given(purchaseOrderRepository.findAll()).willReturn(purchaseOrders);

        //when
        List<PurchaseOrder> foundPurchaseOrders = purchaseOrderServiceImpl.findAll();

        //then
        then(purchaseOrderRepository).should().findAll();
        assertThat(foundPurchaseOrders).hasSize(1);
    }

    @Test
    void findById() {
        //given
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        given(purchaseOrderRepository.existsById(ID)).willReturn(true);
        when(purchaseOrderRepository.findById(ID)).thenReturn(Optional.of(purchaseOrder));

        //when
        Optional<PurchaseOrder> foundPurchaseOrder = purchaseOrderServiceImpl.findById(ID);

        //then
        then(purchaseOrderRepository).should().findById(ID);
        assertThat(foundPurchaseOrder).isNotNull();
    }



    @Test
    void deleteById_whenIdIdFound() {
        //given
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        given(purchaseOrderRepository.existsById(ID)).willReturn(true);
        when(purchaseOrderRepository.findById(ID)).thenReturn(Optional.of(purchaseOrder));
        //when
        purchaseOrderServiceImpl.deleteById(ID);
        //then
        then(purchaseOrderRepository).should(times(1)).delete(purchaseOrder);
    }

    @Test
    void deleteById_whenIdIsNotFoundThrowsNotFoundException() {

        //given
        given(purchaseOrderRepository.existsById(ID)).willReturn(false);

        //then
        assertThrows(ResourceNotFoundException.class, () -> {
            purchaseOrderServiceImpl.deleteById(ID);
        });
    }
}