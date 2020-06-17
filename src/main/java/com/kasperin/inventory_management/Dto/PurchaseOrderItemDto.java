package com.kasperin.inventory_management.Dto;

import com.kasperin.inventory_management.domain.commerce.PaymentType;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PurchaseOrderItemDto {

    private String MemberNumber;

    private PaymentType paymentType;

    Map<String, Integer> items = new HashMap<>();
}
