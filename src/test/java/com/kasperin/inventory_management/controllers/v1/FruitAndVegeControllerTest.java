package com.kasperin.inventory_management.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.controllers.exceptions.RestResponseEntityExceptionHandler;
import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.services.itemsServices.FruitAndVegeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
class FruitAndVegeControllerTest {

    public static final String NAME = "Apple";
    public static final String BARCODE = "12345";
    public static final Long ID = 1L;
    public static final double price = 1.2;

    ObjectMapper objectMapper;

    @Mock
    FruitAndVegeService fruitAndVegeService;
    FruitAndVegeController controller;

    @InjectMocks
    FruitAndVegeController fruitAndVegeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception{
        objectMapper = new ObjectMapper();
        controller = new FruitAndVegeController(fruitAndVegeService);

        mockMvc = MockMvcBuilders.standaloneSetup(fruitAndVegeController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void findAll() throws Exception{
        FruitAndVege fav1 = new FruitAndVege();
        fav1.setId(ID);
        fav1.setBarcode(BARCODE);
        fav1.setName(NAME);

        FruitAndVege fav2 = new FruitAndVege();
        fav2.setId(2L);
        fav2.setBarcode("0987");
        fav2.setName("banana");

        List<FruitAndVege> fruitAndVeges = Arrays.asList(fav1,fav2);

        when(fruitAndVegeService.findAll()).thenReturn(fruitAndVeges);

        mockMvc.perform(
                get("/api/v1/fruitAndVeges").contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));

        verify(fruitAndVegeService).findAll();
        verify(fruitAndVegeService, never()).findById(any());
    }

    @Test
    void findByName() throws Exception{
            FruitAndVegeDTO fruitAndVege1 = new FruitAndVegeDTO();
            //fruitAndVege1.setId(1l);
            fruitAndVege1.setName(NAME);

            when(fruitAndVegeService.findByName(anyString())).thenReturn(fruitAndVege1);

            mockMvc.perform(get("/api/v1/fruitAndVeges/banana")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", equalTo(NAME)));

            verify(fruitAndVegeService).findByName(NAME);

    }
}