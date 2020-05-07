//package com.kasperin.inventory_management.controllers.v1;
//
//import com.kasperin.inventory_management.domain.Stationary;
//import com.kasperin.inventory_management.services.StationaryService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.anyLong;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class StationaryControllerTest {
//
//    @Mock
//    StationaryService stationaryService;
//
//    @InjectMocks
//    StationaryController controller;
//
//    MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }
//
//    @Test
//    void getAllStationary() throws Exception {
//            // given
//            Stationary st1 = new Stationary();
//            st1.setId(1L);
//            Stationary st2 = new Stationary();
//            st2.setId(2L);
//            List<Stationary> stationary = Arrays.asList(st1, st2);
//
//            when(stationaryService.findAll()).thenReturn(stationary);
//
//            // when
//            mockMvc.perform(
//                    get("/api/v1/stationary/").contentType(MediaType.APPLICATION_JSON))
//                    // then
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$[0].id").value(1L))
//                    .andExpect(jsonPath("$[1].id").value(2L));
//
//            verify(stationaryService).findAll();
//
//    }
//
//    @Test
//    void getByName() throws Exception {
//        final Long id = 1L;
//        final String name = "glue";
//
//        // given
//        Stationary st = new Stationary();
//        st.setId(id);
//        st.setName(name);
//
//        when(stationaryService.findByName(anyString())).thenReturn(Optional.of(st));
//
//        // when
//        mockMvc.perform(get("/api/v1/stationary/glue").accept(MediaType.APPLICATION_JSON))
//                // then
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name));
//
//        verify(stationaryService).findByName(eq(name));
//    }
//
//    @Test
//    void getByName_notFound() throws Exception {
//        // given
//        when(stationaryService.findByName(anyString())).thenReturn(Optional.empty());
//
//        // when
//        mockMvc.perform(get("/api/v1/stationary/by-name/glue").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void getById() throws Exception {
//        // given
//        Stationary pf = new Stationary();
//        pf.setId(1L);
//
//        when(stationaryService.findById(anyLong())).thenReturn(Optional.of(pf));
//
//        // when
//        mockMvc.perform(get("/api/v1/stationary/1").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1L));
//
//        verify(stationaryService).findById(anyLong());
//    }
//
//    @Test
//    void getById_notFound() throws Exception {
//        // given
//        when(stationaryService.findById(anyLong())).thenReturn(Optional.empty());
//
//        // when
//        mockMvc.perform(get("/api/v1/stationary/1").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//
//        verify(stationaryService).findById(anyLong());
//    }
//
//    @Test
//    void createNewStationary() throws Exception {
//        ArgumentCaptor<Stationary> stationaryCaptor = ArgumentCaptor.forClass(Stationary.class);
//        // given
//        Stationary pf = new Stationary();
//        pf.setId(1L);
//
//        when(stationaryService.save(any())).thenReturn(pf);
//
//        // when
//        mockMvc.perform(
//                post("/api/v1/stationary")
////                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(pf)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1L));
//
//        verify(stationaryService).save(stationaryCaptor.capture());
//
//        assertEquals(pf.getId(), stationaryCaptor.getValue().getId());
//    }
//
//    @Test
//    void deleteById() throws Exception {
//        // when
//        mockMvc.perform(delete("/api/v1/stationary/1"))
//                .andExpect(status().isOk());
//
//        verify(stationaryService).deleteById(anyLong());
//    }
//}