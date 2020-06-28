package com.kasperin.inventory_management.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecommendItemDto {

    private Long memberId;

    private List<String> itemBarcodes;
}
