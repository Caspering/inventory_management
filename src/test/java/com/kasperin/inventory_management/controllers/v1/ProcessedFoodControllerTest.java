package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.services.ProcessedFoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProcessedFoodControllerTest {

    @Mock
    ProcessedFoodService processedFoodService;

    ProcessedFoodController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        controller = new ProcessedFoodController(processedFoodService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAll() throws Exception {
        // given
        ProcessedFood pf1 = new ProcessedFood();
        pf1.setId(1L);
        ProcessedFood pf2 = new ProcessedFood();
        pf2.setId(2L);
        List<ProcessedFood> processedFoods = Arrays.asList(pf1, pf2);

        when(processedFoodService.findAll()).thenReturn(processedFoods);

        // when
        mockMvc.perform(
                get("/api/v1/processedFoods").contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    void findAllByType() {

    }

    @Test
    void findByName() {
    }

    @Test
    void findById() {
    }

    @Test
    void createNewProcessedFood() {
    }

    @Test
    void deleteById() {
    }
}