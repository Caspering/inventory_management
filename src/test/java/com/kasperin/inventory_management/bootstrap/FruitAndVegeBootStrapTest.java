package com.kasperin.inventory_management.bootstrap;

import com.kasperin.inventory_management.model.FruitAndVege;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FruitAndVegeBootStrapTest {

    @Autowired
    FruitAndVegeRepository fruitAndVegeRepository;

    @Test
    void onApplicationEvent() {
        FruitAndVege fav = fruitAndVegeRepository.findByName("fav1");
        assertNotNull(fav);
        assertEquals("fav1", fav.getName());
        assertEquals("123", fav.getBarcode());
        assertEquals(5, fav.getInStockQuantity());
        assertEquals(2.99, fav.getPrice(), 0.01);

        // todo: verify other FruitAndVege objects when they're added
    }
}