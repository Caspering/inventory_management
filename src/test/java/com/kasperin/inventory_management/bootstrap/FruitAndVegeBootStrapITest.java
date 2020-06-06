package com.kasperin.inventory_management.bootstrap;

import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FruitAndVegeBootStrapITest {

    @Autowired
    FruitAndVegeRepository fruitAndVegeRepository;

    @Test
    void onApplicationEvent() {
       List<FruitAndVege> foo = fruitAndVegeRepository.findAll();

       assertEquals(3, foo.size());

    }
}