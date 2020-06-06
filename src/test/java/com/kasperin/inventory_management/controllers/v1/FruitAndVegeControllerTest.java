//package com.kasperin.inventory_management.controllers.v1;
//
//import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
//import com.kasperin.inventory_management.services.itemsServices.FruitAndVegeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class FruitAndVegeControllerTest {
//
//    public static final String NAME = "Apple";
//    public static final String BARCODE = "12345";
//    public static final Long ID = 1L;
//    public static final double price = 1.2;
//
//    @Mock
//    FruitAndVegeService fruitAndVegeService;
//
//    @InjectMocks
//    FruitAndVegeController fruitAndVegeController;
//
//    MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() throws Exception{
//        MockitoAnnotations.initMocks(this);
//
//        mockMvc = MockMvcBuilders.standaloneSetup(fruitAndVegeController).build();
//    }
//
//    @Test
//    void findAll() throws Exception{
//        FruitAndVegeDTO fav1 = new FruitAndVegeDTO();
//        fav1.setId(ID);
//        fav1.setBarcode(BARCODE);
//        fav1.setName(NAME);
//
//        FruitAndVegeDTO fav2 = new FruitAndVegeDTO();
//        fav2.setId(2L);
//        fav2.setBarcode("0987");
//        fav2.setName("banana");
//
//        List<FruitAndVegeDTO> fruitAndVegeDTOS = Arrays.asList(fav1,fav2);
//
//        when(fruitAndVegeService.findAll()).thenReturn(fruitAndVegeDTOS);
//    }
//
//    @Test
//    void findByName() throws Exception{
//            FruitAndVegeDTO fruitAndVege1 = new FruitAndVegeDTO();
//            fruitAndVege1.setId(1l);
//            fruitAndVege1.setName(NAME);
//
//            when(fruitAndVegeService.findByName(anyString())).thenReturn(fruitAndVege1);
//
//            mockMvc.perform(get("/api/v1/fruitAndVeges/banana")
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.name", equalTo(NAME)));
//
//    }
//}