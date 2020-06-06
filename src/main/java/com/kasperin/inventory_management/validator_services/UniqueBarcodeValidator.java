//package com.kasperin.inventory_management.validator_services;
//
//import com.kasperin.inventory_management.domain.Items.FruitAndVege;
//import com.kasperin.inventory_management.domain.Items.ProcessedFood;
//import com.kasperin.inventory_management.domain.Items.Stationary;
//import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
//import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
//import com.kasperin.inventory_management.repository.StationaryRepository;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import java.time.LocalDate;
//@Slf4j
//public class UniqueBarcodeValidator implements ConstraintValidator<UniqueBarcode, String> {
//
//    private StationaryRepository stationaryRepository;
//    private FruitAndVegeRepository fruitAndVegeRepository;
//    private ProcessedFoodRepo processedFoodRepo;
//
//
//    private Stationary stationary;
//    private FruitAndVege fruitAndVege;
//    private ProcessedFood processedFood;
//
//
//    @Override
//    public void initialize(UniqueBarcode constraintAnnotation) {
//        this.stationary.getBarcode() = constraintAnnotation.barcode();
//    }
//
//
//    @Override
//    public boolean isValid(String barcode, ConstraintValidatorContext constraintValidatorContext) {
//
//        if ( barcode == null ) {
//            return true;
//        }
//
//        if (stationaryRepository.existsByBarcode(barcode)){
//            return false;
//        }else if (fruitAndVegeRepository.existsByBarcode(barcode)){
//               return false;
//        } else if (processedFoodRepo.existsByBarcode(barcode)){
//        return false;
//        }
//    return true;
//    }
//}
