package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.model.AssociationDto;
import com.kasperin.inventory_management.api.v1.model.RecommendItemDto;
import com.kasperin.inventory_management.domain.Items.Item;


public interface RecommendationService {


    Item getRecommendedFrom(RecommendItemDto itemList);

    void createAssociation(AssociationDto associationDTO);
}
