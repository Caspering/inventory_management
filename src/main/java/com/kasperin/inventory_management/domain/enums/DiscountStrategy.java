package com.kasperin.inventory_management.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DiscountStrategy {

    @JsonProperty("no")
    NO(0.0),

    @JsonProperty("half off entire")
    HALF_OFF_ENTIRE(0.5),

    @JsonProperty("quarter off entire")
    QUARTER_OFF_ENTIRE(0.25);

    public Double discountRate;

    DiscountStrategy(Double rate) {
        discountRate = rate;
    }


}
