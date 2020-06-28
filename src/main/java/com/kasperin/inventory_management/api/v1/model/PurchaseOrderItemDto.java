package com.kasperin.inventory_management.api.v1.model;

import com.kasperin.inventory_management.domain.enums.DiscountStrategy;
import com.kasperin.inventory_management.domain.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderItemDto {

    @NotBlank
    private String MemberNumber;

    private PaymentType paymentType;

    private DiscountStrategy discountStrategy;

    Map<String, Integer> items = new HashMap<>();
}
