package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.api.v1.model.AssociationDto;
import com.kasperin.inventory_management.api.v1.model.RecommendItemDto;
import com.kasperin.inventory_management.domain.Items.Item;
import com.kasperin.inventory_management.services.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(RecommendationController.BASE_URL)
@RequiredArgsConstructor
public class RecommendationController {
    public static final String BASE_URL = "/api/v1/recommendations";

    private final RecommendationService recommendationService;

    @PostMapping("")
    public Item recommend(@RequestBody RecommendItemDto itemList) {
        log.info("POST request to get recommendations from {}",itemList);
        return recommendationService.getRecommendedFrom(itemList);
    }

    @PostMapping("/associate")
    public ResponseEntity<String> createAssociation(@RequestBody AssociationDto associationDTO)  {
        log.info("POST request to get recommendations from {}", associationDTO);
        recommendationService.createAssociation(associationDTO);
        return ResponseEntity.ok().build();
    }
}
