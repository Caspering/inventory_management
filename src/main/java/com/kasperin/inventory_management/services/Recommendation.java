package com.kasperin.inventory_management.services;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class Recommendation {
    private final Map<String, String> associations;

    public Recommendation() {
        associations = new ConcurrentHashMap<>();
    }


    public String getAssociatedBarcodeOf(String productBarcode){
        return associations.get(productBarcode);
    }

    public void setAssociationFor(String keyBarcode, String associatedBarcode){
        associations.putIfAbsent(keyBarcode, associatedBarcode);
    }
}
