package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.mapper.FruitAndVegeMapper;
import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.controllers.v1.FruitAndVegeController;
import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import com.kasperin.inventory_management.validator_services.OnCreate;
import com.kasperin.inventory_management.validator_services.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FruitAndVegeServiceImpl implements FruitAndVegeService {

    private final FruitAndVegeMapper fruitAndVegeMapper;
    private final FruitAndVegeRepository fruitAndVegeRepository;

    private FruitAndVegeDTO getFruitAndVegeDTOByNameIgnoreCase(String name) {
        if (fruitAndVegeRepository.existsByNameIgnoreCase(name)) {
            return fruitAndVegeMapper.fruitAndVegeToFruitAndVegeDTO(fruitAndVegeRepository.findByNameIgnoreCase(name));
        }else{
            throw new ResourceNotFoundException("The fruit or vegetable with name: "
                    + name + " does not exist");
        }
    }
    private Optional<FruitAndVege> getFruitAndVegeById(Long id){
        if (fruitAndVegeRepository.existsById(id)) {
            return fruitAndVegeRepository.findById(id);
        }else{
            throw new ResourceNotFoundException
                    ("The Fruit or Vegetable object with the requested id: "+ id +" was not found");
        }
    }


    @Override
    @Validated(OnCreate.class)
    public FruitAndVegeDTO saveAndReturnDTO(@Valid FruitAndVege fruitAndVege) {
        FruitAndVege savedFruitAndVege = fruitAndVegeRepository.save(fruitAndVege);

        FruitAndVegeDTO returnDto = fruitAndVegeMapper.fruitAndVegeToFruitAndVegeDTO(savedFruitAndVege);

        return returnDto;
    }

    @Override
    @Validated(OnCreate.class)
    public FruitAndVegeDTO createNewFruitAndVege(@Valid FruitAndVegeDTO fruitAndVegeDTO) {

        return saveAndReturnDTO(fruitAndVegeMapper.fruitAndVegeDTOtoFruitAndVege(fruitAndVegeDTO));
    }

    @Override
    @Validated(OnUpdate.class)
    public Optional<FruitAndVege> updateById(Long id, @Valid FruitAndVege fruitAndVegePatch) {
        return getFruitAndVegeById(id)
                .map(fruitAndVegeInDB -> {
                    if (fruitAndVegePatch.getInStockQuantity() == null)
                        fruitAndVegePatch.setInStockQuantity(fruitAndVegeInDB.getInStockQuantity());

                    if(fruitAndVegePatch.getInStockQuantity() >= 0) {
                        fruitAndVegeInDB.setInStockQuantity(fruitAndVegePatch.getInStockQuantity());

                        if (fruitAndVegePatch.getName() != null)
                            fruitAndVegeInDB.setName(fruitAndVegePatch.getName());

                        if (fruitAndVegePatch.getBarcode() != null)
                            fruitAndVegeInDB.setBarcode(fruitAndVegePatch.getBarcode());

                        if (fruitAndVegePatch.getPrice() != null)
                            fruitAndVegeInDB.setPrice(fruitAndVegePatch.getPrice());

                        log.info("The Fruit or vegetable: " + fruitAndVegeInDB.getName() + " was updated");
                        return fruitAndVegeRepository.save(fruitAndVegeInDB);
                    }
                return fruitAndVegeInDB;
        });
    }

    @Override
    public List<FruitAndVege> findAll() {
        return fruitAndVegeRepository.findAll();
    }

    @Override
    public List<FruitAndVege> findAllInStock() {
        return fruitAndVegeRepository.findAll()
                .stream()
                .filter(fruitAndVege -> fruitAndVege.getInStockQuantity()>=1)
                .collect(Collectors.toList());
    }

    @Override
    public FruitAndVegeDTO findById(Long id) {
        return fruitAndVegeRepository.findById(id)
                .map(fruitAndVegeMapper::fruitAndVegeToFruitAndVegeDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public FruitAndVegeDTO findByName(String name) {
        return getFruitAndVegeDTOByNameIgnoreCase(name);
    }

    @Override
    public void deleteById(Long id) {
        getFruitAndVegeById(id).map(fruitAndVege -> {fruitAndVegeRepository.delete(fruitAndVege);
            return null;
        });
    }

}
