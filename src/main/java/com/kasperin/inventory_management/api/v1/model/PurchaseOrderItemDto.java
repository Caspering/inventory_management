package com.kasperin.inventory_management.api.v1.model;

import com.kasperin.inventory_management.domain.enums.DiscountStrategy;
import com.kasperin.inventory_management.domain.enums.PaymentType;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PurchaseOrderItemDto {

    private String MemberNumber;

    private PaymentType paymentType;

    private DiscountStrategy discountStrategy;

    Map<String, Integer> items = new HashMap<>();

}
