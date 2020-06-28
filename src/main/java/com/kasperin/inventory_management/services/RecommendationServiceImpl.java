package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.model.AssociationDto;
import com.kasperin.inventory_management.api.v1.model.RecommendItemDto;
import com.kasperin.inventory_management.controllers.exceptions.ResourceNotFoundException;
import com.kasperin.inventory_management.domain.Items.Item;
import com.kasperin.inventory_management.domain.customer.Member;
import com.kasperin.inventory_management.repository.ItemsRepository.FruitAndVegeRepository;
import com.kasperin.inventory_management.services.customerServices.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class RecommendationServiceImpl implements RecommendationService{


    private final MemberService customerService;
    //private final FruitAndVegeService fruitAndVegeService;

    // private final ProductRepository productRepository;
    private final FruitAndVegeRepository fruitAndVegeRepository;

    //@Autowired
    private final Recommendation dictionary;

    /*public RecommendationService(CustomerService customerService, ProductRepository productRepository) {
        this.customerService = customerService;
        this.productRepository = productRepository;
    }*/

    @Override
    public Item getRecommendedFrom(RecommendItemDto itemList){
        Member currentCustomer;


        if(!(itemList.getMemberId()==null || !(customerService.findById(itemList.getMemberId()).isPresent()))){
            currentCustomer = customerService.findById(itemList.getMemberId()).get();
        }
        else throw  new ResourceNotFoundException("Customer not present in recommendation request!");
        //log.warn("Customer not present in recommendation request!");

        List<Item> inputProducts = getProductListFromIdList(itemList.getItemBarcodes());
        log.info("Input products {}",inputProducts);

        return computeRecommendations(currentCustomer,inputProducts);
    }

    private List<Item> getProductListFromIdList(List<String> itemBarcode) {
        List<Item> result = new LinkedList<>();
        itemBarcode.forEach(id -> {
            if (fruitAndVegeRepository.existsByBarcode(id)) {
                result.add(fruitAndVegeRepository.findByBarcode(id));
            }//else if (stationary
        });
        return result;
    }

    private Item computeRecommendations(Member member, List<Item> inputProducts) {
        String keyBarcode = inputProducts.get(0).getBarcode();
        return fruitAndVegeRepository.findByBarcode(dictionary.getAssociatedBarcodeOf(keyBarcode));

    }

    @Override
    public void createAssociation(AssociationDto associationDTO) {
        if((associationDTO.getKeyBarcode() != null && associationDTO.getAssociatedBarcode() != null)) {
            if(fruitAndVegeRepository.existsByBarcode(associationDTO.getKeyBarcode())) {
                if (fruitAndVegeRepository.existsByBarcode(associationDTO.getAssociatedBarcode())) {
                    dictionary.setAssociationFor(associationDTO.getKeyBarcode(), associationDTO.getAssociatedBarcode());
                }else throw new ResourceNotFoundException("fruit and vege item with associated barcode can not be found");
            }else throw new ResourceNotFoundException("fruit and vege item with key barcode can not be found");
        }else throw new RuntimeException("key barcode or associated barcode cant be null");
    }
}
