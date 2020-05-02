package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.mapper.FruitAndVegeMapper;
import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.api.v1.model.FruitAndVegeListDTO;
import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FruitAndVegeServiceImpl implements  FruitAndVegeService{

    private final FruitAndVegeMapper fruitAndVegeMapper;

    private final FruitAndVegeRepository fruitAndVegeRepository;

    public FruitAndVegeServiceImpl(FruitAndVegeMapper fruitAndVegeMapper, FruitAndVegeRepository fruitAndVegeRepository) {
        this.fruitAndVegeMapper = fruitAndVegeMapper;
        this.fruitAndVegeRepository = fruitAndVegeRepository;
    }

//    @Override
//    public FruitAndVege save(FruitAndVege fruitAndVege) {
//        return fruitAndVegeRepository.save(fruitAndVege);
//    }
//
//    @Override
//    public Optional<FruitAndVegeDTO> findById(Long id) {
//        return fruitAndVegeRepository.findById(id).map(fruitAndVegeMapper::fruitAndVegeToFruitAndVegeDTO);
//    }

    @Override
    public FruitAndVegeDTO findByName(String name) {
        return fruitAndVegeMapper.fruitAndVegeToFruitAndVegeDTO(fruitAndVegeRepository.findByName(name));
    }

    @Override
    public List<FruitAndVegeDTO> findAll() {
        return fruitAndVegeRepository.findAll()
                .stream()
                .map(fruitAndVegeMapper::fruitAndVegeToFruitAndVegeDTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public void deleteById(Long id) {
//        fruitAndVegeRepository.deleteById(id);
//    }
}
