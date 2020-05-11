//package com.kasperin.inventory_management.bootstrap;
//
//import com.kasperin.inventory_management.domain.FoodType;
//import com.kasperin.inventory_management.domain.FruitAndVege;
//import com.kasperin.inventory_management.domain.ProcessedFood;
//import com.kasperin.inventory_management.domain.Stationary;
//import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
//import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
//import com.kasperin.inventory_management.repository.StationaryRepository;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Profile;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Component
//@AllArgsConstructor
//@Profile("default")
//public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
//
//    private final FruitAndVegeRepository fruitAndVegeRepository;
//
//    private final StationaryRepository stationaryRepository;
//
//    private final ProcessedFoodRepo processedFoodRepo;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//
//        List<FruitAndVege> savedFAV = fruitAndVegeRepository.saveAll(getFruitAndVeges());
//        log.info("Produce saved: {}", savedFAV);
//
//        List<ProcessedFood> savedPF = processedFoodRepo.saveAll(getProcessedFood());
//        log.info("Processed foods saved: {}", savedPF);
//
//        List<Stationary> savedSt = stationaryRepository.saveAll(getStationary());
//        log.info("Stationary saved: {}", savedSt);
//
////        List<Stationary> savedFromCsv = stationaryRepository.saveAll(saveCsv());
////        log.info("Stationary saved: {}", saveCsv());
//    }
//
//
////    private List<Stationary> saveCsv() {
////        String line = "";
////        List<Stationary> stats = new ArrayList<>();
////        try {
////            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/fruit_vege.csv"));
////            while ((line=br.readLine())!=null){
////                String [] data = line.split(",");
////
////                Stationary s = new Stationary();
////                s.setName(data[0]);
////                s.setBarcode(data[1]);
////                s.setPrice(Double.valueOf(data[2]));
////                s.setInStockQuantity(Integer.valueOf(data[3]));
////                stats.add(s);
////
////            }
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        return stats;
////    }
//
//
//    private List<FruitAndVege> getFruitAndVeges(){
//
//        List<FruitAndVege> fruitAndVeges = new ArrayList<>();
//        FruitAndVege fav1 = new FruitAndVege();
//        fav1.setName("fav1");
//        fav1.setBarcode("123");
//        fav1.setInStockQuantity(5);
//        fav1.setPrice(2.99);
//
//        fruitAndVeges.add(fav1);
//
//        FruitAndVege fruitAndVege2 = new FruitAndVege();
//        fruitAndVege2.setName("Orange");
//        fruitAndVege2.setBarcode("123456");
//        fruitAndVege2.setInStockQuantity(6);
//        fruitAndVege2.setPrice(3.8);
//
//        fruitAndVeges.add(fruitAndVege2);
//
//        FruitAndVege fruitAndVege3 = new FruitAndVege();
//        fruitAndVege3.setName("Pineapple");
//        fruitAndVege3.setBarcode("133006");
//        fruitAndVege3.setInStockQuantity(8);
//        fruitAndVege3.setPrice(3.8);
//
//        fruitAndVeges.add(fruitAndVege3);
//
//        return fruitAndVeges;
//    }
//
//    private List<ProcessedFood> getProcessedFood() {
//
//        List<ProcessedFood> proFood = new ArrayList<>();
//
//        ProcessedFood savedProcessedFood = new ProcessedFood();
//        savedProcessedFood.setName("Chips");
//        savedProcessedFood.setBarcode("12345");
//        savedProcessedFood.setPrice(1.3);
//        savedProcessedFood.setFoodType(FoodType.VEGAN);
//
//        proFood.add(savedProcessedFood);
//
//
//        ProcessedFood proFood2 = new ProcessedFood();
//        proFood2.setName("burger");
//        proFood2.setBarcode("12345");
//        proFood2.setPrice(1.3);
//        proFood2.setFoodType(FoodType.VEGAN);
//
//        proFood.add(proFood2);
//
//        ProcessedFood proFood3 = new ProcessedFood();
//        proFood3.setName("fries");
//        proFood3.setBarcode("12345");
//        proFood3.setPrice(1.3);
//        proFood3.setFoodType(FoodType.NONVEGAN);
//
//        proFood.add(proFood3);
//
//        return proFood;
//
//    }
//
//    private List<Stationary> getStationary() {
//
//        List<Stationary> stationaries = new ArrayList<>();
//
//        Stationary st1 = new Stationary();
//        st1.setName("Glue");
//        st1.setBarcode("12345");
//        st1.setPrice(1.3);
//
//        stationaries.add(st1);
//
//
//        Stationary st2 = new Stationary();
//        st2.setName("Pencil");
//        st2.setBarcode("12345");
//        st2.setPrice(1.3);
//
//        stationaries.add(st2);
//
//
//        Stationary st3 = new Stationary();
//        st3.setName("Ink");
//        st3.setBarcode("12345");
//        st3.setPrice(1.3);
//
//        stationaries.add(st3);
//
//
//        Stationary st4 = new Stationary();
//        st4.setName("Ruler");
//        st4.setBarcode("12445");
//        st4.setPrice(1.4);
//
//        stationaries.add(st4);
//
//
//        Stationary st5 = new Stationary();
//        st5.setName("Eraser");
//        st5.setBarcode("12545");
//        st5.setPrice(1.5);
//
//        stationaries.add(st5);
//
//
//        return stationaries;
//
//    }
//}
