package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.mapper.FruitAndVegeMapper;
import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.repository.ItemsRepository.FruitAndVegeRepository;
import com.kasperin.inventory_management.services.itemsServices.FruitAndVegeService;
import com.kasperin.inventory_management.services.itemsServices.FruitAndVegeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Disabled
class FruitAndVegeServiceImplTest {

    public static final Long ID = 1L;
    public static final Long ID2 = 2L;
    public static final String NAME = "Banana";
    public static final String NAME2 = "Apple";
    public static final String BARCODE = "123456";
    public static final String BARCODE2 = "789012";
    public static final double PRICE = 1.9;
    public static final double PRICE2 = 1.9;

    @InjectMocks
    FruitAndVegeService fruitAndVegeService;

    FruitAndVegeMapper fruitAndVegeMapper;

    @Mock
    FruitAndVegeRepository fruitAndVegeRepository;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        fruitAndVegeMapper = FruitAndVegeMapper.INSTANCE;

    }

    @Test
    void createNewFruitAndVege() throws Exception {
        //given
        FruitAndVege savedFruitAndVege = new FruitAndVege();
        savedFruitAndVege.setId(ID);
        savedFruitAndVege.setName(NAME);
        savedFruitAndVege.setBarcode(BARCODE);
        savedFruitAndVege.setPrice(PRICE);

        when(fruitAndVegeRepository.save(any()))
                .thenReturn(savedFruitAndVege);

        FruitAndVegeDTO result = fruitAndVegeService.saveAndReturnDTO(savedFruitAndVege);

        verify(fruitAndVegeRepository).save(any());

        assertEquals(savedFruitAndVege, result);

    }

//    @Test
//    void findAll() {
//        //given
//        FruitAndVege fruitAndVege = new FruitAndVege();
//        fruitAndVege.setId(ID);
//        fruitAndVege.setName(NAME);
//        fruitAndVege.setBarcode(BARCODE);
//        fruitAndVege.setPrice(PRICE);
//
//        FruitAndVege fruitAndVege2 = new FruitAndVege();
//        fruitAndVege2.setId(ID2);
//        fruitAndVege2.setName(NAME2);
//        fruitAndVege2.setBarcode(BARCODE2);
//        fruitAndVege2.setPrice(PRICE2);
//
//        when(fruitAndVegeRepository.findAll())
//                .thenReturn(Arrays.asList(fruitAndVege, fruitAndVege2));
//
//        List<FruitAndVegeDTO> result = fruitAndVegeService.findAll();
//
//        verify(fruitAndVegeRepository).findAll();
//
//        assertEquals(Arrays.asList(fruitAndVege, fruitAndVege2), result);
//
//    }

//    @Test
//    void getFruitAndVegeById() {
//        //given
//        FruitAndVege fruitAndVege = new FruitAndVege();
//        fruitAndVege.setId(ID);
//        fruitAndVege.setName(NAME);
//        fruitAndVege.setBarcode(BARCODE);
//        fruitAndVege.setPrice(PRICE);
//
//        when(fruitAndVegeRepository.findById(anyLong()))
//                .thenReturn(java.util.Optional.ofNullable(fruitAndVege));
//
//        Optional<FruitAndVegeDTO> result = fruitAndVegeService.findById(ID);
//
//        verify(fruitAndVegeRepository).findById(ID);
//
//        FruitAndVegeDTO dto = result.get();
//
//        assertEquals(fruitAndVege.getBarcode(), dto.getBarcode());
//        assertEquals(fruitAndVege.getInStockQuantity(), dto.getInStockQuantity());
//        assertEquals(fruitAndVege.getName(), dto.getName());
//        assertEquals(fruitAndVege.getPrice(), dto.getPrice());
//    }

//    @Test
//    void deleteById() {
//
//        fruitAndVegeService.deleteById(ID);
//
//        verify(fruitAndVegeRepository).deleteById(anyLong());
//
//    }
}