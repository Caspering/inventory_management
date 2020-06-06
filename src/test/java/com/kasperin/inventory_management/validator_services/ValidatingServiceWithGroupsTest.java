/*
package com.kasperin.inventory_management.validator_services;

import com.kasperin.inventory_management.domain.Items.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidatingServiceWithGroupsTest {

    @Autowired
    private ValidatingServiceWithGroups service;

    @Test
    void whenItemIsInvalidForCreate_thenThrowExceptio() {
        Item item = validItem();
        item.setInStockQuantity(0);

        assertThrows(ConstraintViolationException.class, () -> {
            service.validateForUpdate(item);
        });
    }

    @Test
    void whenItemIsInvalidForUpdate_thenThrowExceptio() {
        Item item = validItem();
        item.setInStockQuantity(-1);

        assertThrows(ConstraintViolationException.class, () -> {
            service.validateForUpdate(item);
        });
    }
}*/
