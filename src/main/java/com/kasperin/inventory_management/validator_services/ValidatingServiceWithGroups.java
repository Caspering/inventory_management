package com.kasperin.inventory_management.validator_services;

import com.kasperin.inventory_management.domain.Item;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;

//import javax.validation.Valid;
//
//@Service
//@Validated
//public class ValidatingServiceWithGroups {
//
//    @Validated(OnCreate.class)
//    void validateForCreate(@Valid Item item){
//        //do somethin
//    }
//
//    @Validated(OnUpdate.class)
//    void validateForUpdate(@Valid Item item){
//        if (item.getInStockQuantity() >= 1) {
//            System.out.println("Valid input");
//        }
//    }
//
//}
