package com.kasperin.inventory_management.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DiscountStrategy {

    @JsonProperty("no")
    NO(0.0F),

    @JsonProperty("half off entire")
    HALF_OFF_ENTIRE(0.5F),

    @JsonProperty("quarter off entire")
    QUARTER_OFF_ENTIRE(0.25F);

    public final Float discountRate;

    DiscountStrategy(Float rate) {
        discountRate = rate;
    }


}
