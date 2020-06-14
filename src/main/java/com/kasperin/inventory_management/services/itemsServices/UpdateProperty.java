package com.kasperin.inventory_management.services.itemsServices;

import com.kasperin.inventory_management.domain.Items.Stationary;

import javax.validation.Valid;

public class UpdateProperty {
    public void updateQty(@Valid Stationary stationaryPatch, Stationary stationaryInDB) {
        if(stationaryPatch.getInStockQuantity() >= 0)
            stationaryInDB.setInStockQuantity(stationaryPatch.getInStockQuantity());
    }

    public void updateName(@Valid Stationary stationaryPatch, Stationary stationaryInDB) {
        if (stationaryPatch.getName() != null)
            stationaryInDB.setName(stationaryPatch.getName());
    }

    public void updateBarcode(@Valid Stationary stationaryPatch, Stationary stationaryInDB) {
        if (stationaryPatch.getBarcode() != null)
            stationaryInDB.setBarcode(stationaryPatch.getBarcode());
    }

    public void updatePrice(@Valid Stationary stationaryPatch, Stationary stationaryInDB) {
        if (stationaryPatch.getPrice() != null)
            stationaryInDB.setPrice(stationaryPatch.getPrice());
    }

    public void patchNullQty(@Valid Stationary stationaryPatch, Stationary stationaryInDB) {
        if (stationaryPatch.getInStockQuantity() == null)
            stationaryPatch.setInStockQuantity(stationaryInDB.getInStockQuantity());
    }
}
