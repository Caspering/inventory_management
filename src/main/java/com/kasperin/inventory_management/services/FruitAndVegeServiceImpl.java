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
    public Optional<FruitAndVege> updateById(Long id, @Valid FruitAndVege newFruitAndVegeData) {
        return getFruitAndVegeById(id)
                .map(oldFruitAndVegeData -> {
                    if (newFruitAndVegeData.getInStockQuantity() == null)
                        newFruitAndVegeData.setInStockQuantity(oldFruitAndVegeData.getInStockQuantity());

                    if(newFruitAndVegeData.getInStockQuantity() >= 1) {
                        oldFruitAndVegeData.setInStockQuantity(newFruitAndVegeData.getInStockQuantity());

                        if (newFruitAndVegeData.getName() != null)
                            oldFruitAndVegeData.setName(newFruitAndVegeData.getName());

                        if (newFruitAndVegeData.getBarcode() != null)
                            oldFruitAndVegeData.setBarcode(newFruitAndVegeData.getBarcode());

                        if (newFruitAndVegeData.getPrice() != null)
                            oldFruitAndVegeData.setPrice(newFruitAndVegeData.getPrice());

                        return fruitAndVegeRepository.save(oldFruitAndVegeData);

                    }else if (newFruitAndVegeData.getInStockQuantity() == 0)
                            fruitAndVegeRepository.delete(oldFruitAndVegeData);
                return null;
        });
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
    public List<FruitAndVege> findAll() {
       return fruitAndVegeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        getFruitAndVegeById(id).map(fruitAndVege -> {fruitAndVegeRepository.delete(fruitAndVege);
            return null;
        });
    }

}
