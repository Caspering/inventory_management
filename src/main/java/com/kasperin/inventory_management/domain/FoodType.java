package com.kasperin.inventory_management.domain;


public enum FoodType {

//    VEGAN('v'), NONVEGAN('n');

    VEGAN, NONVEGAN, UNSPECIFIED;


//    public final char typeCode;

//   FoodType(char typeCode) {
//        this.typeCode = typeCode;
//    }

    public FoodType toString(String s) {
        switch (s) {
            case "v":
                return FoodType.VEGAN;
            case "n":
                return FoodType.NONVEGAN;
            default:
                return FoodType.UNSPECIFIED;
        }
    }

}
