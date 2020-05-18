package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.services.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "All items API")
@RestController
@RequiredArgsConstructor
@RequestMapping(ItemController.BASE_URL)
public class ItemController {

    public static final String BASE_URL = "/api/v1/items";

    private final ItemService itemService;

    @ApiOperation(value = "This will get all items in the inventory.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Object> getAllItems(){
        return itemService.findAll();
    }

}
