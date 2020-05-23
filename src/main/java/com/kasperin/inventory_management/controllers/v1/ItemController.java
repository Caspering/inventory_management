package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.services.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "All items API")
@RestController
@RequiredArgsConstructor
@RequestMapping(ItemController.BASE_URL)
public class ItemController {

    public static final String BASE_URL = "/api/v1/items";

    private final ItemService itemService;

    @ApiOperation(value = "This will get all items in stock in the inventory.",
                  notes = "You can also query the inventory fo all items regardless of in stock status")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Object> getAllItems(@RequestParam(value = "all", defaultValue = "") String all){
        if (all.equals("all")){ return itemService.findAll();
        }
        //Else by default just get what is in stock with qty >=1
        return itemService.findAllInStock();
    }

}
