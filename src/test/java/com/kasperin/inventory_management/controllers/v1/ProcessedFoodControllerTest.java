package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.controllers.RestResponseEntityExceptionHandler;
import com.kasperin.inventory_management.domain.FoodType;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
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

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
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

        verify(processedFoodService).findAll();
        verify(processedFoodService, never()).findByType(any());
    }

    @Test
    void findAllByType_vegan() throws Exception {
        // given
        ProcessedFood pf = new ProcessedFood();
        pf.setId(1L);
        pf.setFoodType(FoodType.VEGAN);

        when(processedFoodService.findByType(any())).thenReturn(Collections.singletonList(pf));

        // when
        mockMvc.perform(get("/api/v1/processedFoods?type=VEGAN")
                .accept(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].foodType").value(FoodType.VEGAN.name()));

        verify(processedFoodService).findByType(eq(FoodType.VEGAN));
        verify(processedFoodService, never()).findAll();
    }

    @Test
    void findAllByType_nonvegan() throws Exception {
        // given
        ProcessedFood pf = new ProcessedFood();
        pf.setId(1L);
        pf.setFoodType(FoodType.NONVEGAN);

        when(processedFoodService.findByType(any())).thenReturn(Collections.singletonList(pf));

        // when
        mockMvc.perform(get("/api/v1/processedFoods?type=NONVEGAN")
                .accept(MediaType.APPLICATION_JSON))
        // then
        .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].foodType").value(FoodType.NONVEGAN.name()));

        verify(processedFoodService).findByType(eq(FoodType.NONVEGAN));
        verify(processedFoodService, never()).findAll();
    }

    @Test
    void findByName() throws Exception {
        final Long id = 1L;
        final String name = "name";

        // given
        ProcessedFood pf = new ProcessedFood();
        pf.setId(id);
        pf.setName(name);

        when(processedFoodService.findByName(anyString())).thenReturn(Optional.of(pf));

        // when
        mockMvc.perform(get("/api/v1/processedFoods/by-name/name").accept(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));

        verify(processedFoodService).findByName(eq(name));
    }

    @Test
    void findByName_notFound() throws Exception {
        // given
        when(processedFoodService.findByName(anyString())).thenReturn(Optional.empty());

        // when
        mockMvc.perform(get("/api/v1/processedFoods/by-name/wat").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void findById() throws Exception {
        // given
        ProcessedFood pf = new ProcessedFood();
        pf.setId(1L);

        when(processedFoodService.findById(anyLong())).thenReturn(Optional.of(pf));

        // when
        mockMvc.perform(get("/api/v1/processedFoods/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(processedFoodService).findById(anyLong());
    }

    @Test
    void findById_notFound() throws Exception {
        // given
        when(processedFoodService.findById(anyLong())).thenReturn(Optional.empty());

        // when
        mockMvc.perform(get("/api/v1/processedFoods/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(processedFoodService).findById(anyLong());
    }

    @Test
    void createNewProcessedFood() {
    }

    @Test
    void deleteById() {
    }
}