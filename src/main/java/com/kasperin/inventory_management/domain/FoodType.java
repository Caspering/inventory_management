package com.kasperin.inventory_management.domain;


public enum FoodType {

    VEGAN('v'),

    NONVEGAN('n');

    public final char typeCode;

    FoodType(char typeCode) {
        this.typeCode = typeCode;
    }

}
