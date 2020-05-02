package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.model.FruitAndVege;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FruitAndVegeServiceImpl implements  FruitAndVegeService{

    private final FruitAndVegeRepository fruitAndVegeRepository;

    public FruitAndVegeServiceImpl(FruitAndVegeRepository fruitAndVegeRepository) {
        this.fruitAndVegeRepository = fruitAndVegeRepository;
    }

    @Override
    public FruitAndVege createNewFruitAndVege(FruitAndVege fruitAndVege) {
        return fruitAndVegeRepository.save(fruitAndVege);
    }

    @Override
    public Optional<FruitAndVege> getFruitAndVegeById(Long id) {
        return fruitAndVegeRepository.findById(id);
    }

    @Override
    public FruitAndVege getFruitAndVegeByName(String name) {
        return fruitAndVegeRepository.findByName(name);
    }

    @Override
    public List<FruitAndVege> getAllFruitAndVeges() {
        return fruitAndVegeRepository.findAll();
    }

    @Override
    public FruitAndVege updateFruitAndVege(Long id, FruitAndVege fruitAndVege) {
        return null;
    }

//    @Override
//    public FruitAndVege updateFruitAndVege(Long id, FruitAndVege fruitAndVege) {
//        return fruitAndVegeRepository.findById(id).map(fruitAndVege -> {
//
//            if (fruitAndVege.getName() != null){
//                fruitAndVege.setName(fruitAndVege.getName());
//            }
//
//            if (fruitAndVege.getPrice() !=0){
//                fruitAndVege.setPrice(fruitAndVege.getPrice());
//            }
//
//            if (fruitAndVege.getBarcode() !=null){
//                fruitAndVege.setBarcode(fruitAndVege.getBarcode());
//            }
//
//            return fruitAndVege;
//
//        } );
//    }

    @Override
    public void deleteFruitAndVegeById(Long id) {
        fruitAndVegeRepository.deleteById(id);
    }
}
