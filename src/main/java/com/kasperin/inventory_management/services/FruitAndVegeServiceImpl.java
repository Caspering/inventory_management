package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.mapper.FruitAndVegeMapper;
import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.controllers.v1.FruitAndVegeController;
import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FruitAndVegeServiceImpl implements FruitAndVegeService {

    private final FruitAndVegeMapper fruitAndVegeMapper;

    private final FruitAndVegeRepository fruitAndVegeRepository;

//    private String getFruitAndVegeUrl(Long id) {
//        return FruitAndVegeController.BASE_URL + "/id/" + id;
//    }

    @Override
    public FruitAndVegeDTO saveAndReturnDTO(FruitAndVege fruitAndVege) {
        FruitAndVege savedFruitAndVege = fruitAndVegeRepository.save(fruitAndVege);

        FruitAndVegeDTO returnDto = fruitAndVegeMapper.fruitAndVegeToFruitAndVegeDTO(savedFruitAndVege);

//        returnDto.setFruitAndVegeUrl(getFruitAndVegeUrl(savedFruitAndVege.getId()));

        return returnDto;
    }

    @Override
    public FruitAndVegeDTO createNewFruitAndVege(FruitAndVegeDTO fruitAndVegeDTO) {

        return saveAndReturnDTO(fruitAndVegeMapper.fruitAndVegeDTOtoFruitAndVege(fruitAndVegeDTO));
    }

    @Override
    public Optional<FruitAndVege> updateById(Long id, FruitAndVege fav) {
        return fruitAndVegeRepository.findById(id).map(fruitAndVege -> {

            if(fav.getInStockQuantity() != 0){
                fruitAndVege.setInStockQuantity(fav.getInStockQuantity());
            }
            if(fav.getName() != null){
                fruitAndVege.setName(fav.getName());
            }
            if(fav.getBarcode() != null){
                fruitAndVege.setBarcode(fav.getBarcode());
            }
            if(fav.getPrice() != null){
                fruitAndVege.setPrice(fav.getPrice());
            }

            return fruitAndVegeRepository.save(fruitAndVege);
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
        return fruitAndVegeMapper.fruitAndVegeToFruitAndVegeDTO(fruitAndVegeRepository.findByName(name));
    }

    @Override
    public List<FruitAndVege> findAll() {
        List <FruitAndVege> fv = fruitAndVegeRepository.findAll();
//        for(FruitAndVege f : fv){
//            f.setFruitAndVegeUrl(getFruitAndVegeUrl(f.getId()));
//        }
        return fv;
    }

    @Override
    public void deleteById(Long id) {
        fruitAndVegeRepository.deleteById(id);
    }

}
