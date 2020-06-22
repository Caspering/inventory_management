package com.kasperin.inventory_management.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentType {

    @JsonProperty("cash")
    CASH,

    @JsonProperty("card")
    CARD
}
