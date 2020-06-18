package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.controllers.v1.Dto.OrderAnalysisDto;
import com.kasperin.inventory_management.services.itemsServices.OrderedItemService;
import com.kasperin.inventory_management.services.itemsServices.OrderedItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(OrderedItemController.BASE_URL)
@RequiredArgsConstructor
//@AllArgsConstructor
public class OrderedItemController {
    public static final String BASE_URL = "/api/v1/orderedItems";
    private final OrderedItemService orderedItemService;
    //private final OrderAnalysisDto orderAnalysisDto;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderAnalysisDto analyzeOrdersByDate(@RequestBody OrderedItemServiceImpl.OrderAnalysisForm form){
        return orderedItemService.analyzeOrdersByDate(form);
    }
}
