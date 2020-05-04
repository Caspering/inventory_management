package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.mapper.FruitAndVegeMapper;
import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.api.v1.model.FruitAndVegeListDTO;
import com.kasperin.inventory_management.controllers.v1.FruitAndVegeController;
import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FruitAndVegeServiceImpl implements FruitAndVegeService {

    private final FruitAndVegeMapper fruitAndVegeMapper;

    private final FruitAndVegeRepository fruitAndVegeRepository;

    public FruitAndVegeServiceImpl(FruitAndVegeMapper fruitAndVegeMapper, FruitAndVegeRepository fruitAndVegeRepository) {
        this.fruitAndVegeMapper = fruitAndVegeMapper;
        this.fruitAndVegeRepository = fruitAndVegeRepository;
    }

    private String getFruitAndVegeUrl(Long id) {
        return FruitAndVegeController.BASE_URL + "/" + id;
    }


    @Override
    public FruitAndVegeDTO createNewFruitAndVege(FruitAndVegeDTO fruitAndVegeDTO) {

        return saveAndReturnDTO(fruitAndVegeMapper.fruitAndVegeDTOtoFruitAndVege(fruitAndVegeDTO));
    }


    //HelperMethod
    @Override
    public FruitAndVegeDTO saveAndReturnDTO(FruitAndVege fruitAndVege) {
        FruitAndVege savedFruitAndVege = fruitAndVegeRepository.save(fruitAndVege);

        FruitAndVegeDTO returnDto = fruitAndVegeMapper.fruitAndVegeToFruitAndVegeDTO(savedFruitAndVege);

        returnDto.setFruitAndVegeUrl(getFruitAndVegeUrl(savedFruitAndVege.getId()));

        return returnDto;
    }

    @Override
    public FruitAndVegeDTO findById(Long id) {
        return fruitAndVegeRepository.findById(id)
                .map(fruitAndVegeMapper::fruitAndVegeToFruitAndVegeDTO)
                .map(fruitAndVegeDTO -> {
                    //set API URL
                    fruitAndVegeDTO.setFruitAndVegeUrl(getFruitAndVegeUrl(id));
                    return fruitAndVegeDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public FruitAndVegeDTO findByName(String name) {
        return fruitAndVegeMapper.fruitAndVegeToFruitAndVegeDTO(fruitAndVegeRepository.findByName(name));
    }

    @Override
    public List<FruitAndVege> findAll() {
        return fruitAndVegeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        fruitAndVegeRepository.deleteById(id);
    }


}
