package com.kasperin.inventory_management.bootstrap;

import com.kasperin.inventory_management.domain.enums.FoodType;
import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.domain.Items.ProcessedFood;
import com.kasperin.inventory_management.domain.Items.Stationary;
import com.kasperin.inventory_management.domain.enums.PaymentType;
import com.kasperin.inventory_management.domain.commerce.PurchaseOrder;
import com.kasperin.inventory_management.domain.customer.Member;
import com.kasperin.inventory_management.repository.ItemsRepository.FruitAndVegeRepository;
import com.kasperin.inventory_management.repository.ItemsRepository.ProcessedFoodRepo;
import com.kasperin.inventory_management.repository.ItemsRepository.StationaryRepository;
import com.kasperin.inventory_management.repository.commerceRepository.PurchaseOrderRepository;
import com.kasperin.inventory_management.repository.customerRepository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
//@Profile("default")
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final FruitAndVegeRepository fruitAndVegeRepository;

    private final StationaryRepository stationaryRepository;

    private final ProcessedFoodRepo processedFoodRepo;

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final MemberRepository memberRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        List<FruitAndVege> savedFAV = fruitAndVegeRepository.saveAll(getFruitAndVeges());
        log.info("Produce saved: {}", savedFAV);

        List<ProcessedFood> savedPF = processedFoodRepo.saveAll(getProcessedFood());
        log.info("Processed foods saved: {}", savedPF);

        List<Stationary> savedSt = stationaryRepository.saveAll(getStationary());
        log.info("Stationary saved: {}", savedSt);

        List<PurchaseOrder> savedPO = purchaseOrderRepository.saveAll(getPurchaseOrder());
        log.info("PurchaseOrder saved: {}", savedPO);

        List<Member> savedMember= memberRepository.saveAll(getMembers());
        log.info("Member saved: {}", savedMember);
    }


    private List<FruitAndVege> getFruitAndVeges(){

        List<FruitAndVege> fruitAndVeges = new ArrayList<>();
        FruitAndVege fav1 = new FruitAndVege();
        fav1.setName("Banana");
        fav1.setBarcode("12335663");
        fav1.setInStockQuantity(5);
        fav1.setPrice(2.99);


        fruitAndVeges.add(fav1);

        FruitAndVege fav2 = new FruitAndVege();
        fav2.setName("Orange");
        fav2.setBarcode("12233323");
        fav2.setInStockQuantity(54);
        fav2.setPrice(1.99);

        fruitAndVeges.add(fav2);

        FruitAndVege fruitAndVege3 = new FruitAndVege();
        fruitAndVege3.setName("Pineapple");
        fruitAndVege3.setBarcode("133006");
        fruitAndVege3.setInStockQuantity(8);
        fruitAndVege3.setPrice(3.8);

        fruitAndVeges.add(fruitAndVege3);

        return fruitAndVeges;
    }

    private Set<FruitAndVege> getFruitAndVegesSet(){
        Set<FruitAndVege> fruitAndVeges = new HashSet<>();
        fruitAndVeges.addAll(getFruitAndVeges());

        return fruitAndVeges;
    }

    private List<ProcessedFood> getProcessedFood() {

        List<ProcessedFood> proFood = new ArrayList<>();

        ProcessedFood savedProcessedFood = new ProcessedFood();
        savedProcessedFood.setName("Chips");
        savedProcessedFood.setBarcode("12345");
        savedProcessedFood.setPrice(1.3);
        savedProcessedFood.setFoodType(FoodType.VEGAN);
        savedProcessedFood.setInStockQuantity(13);
//        savedProcessedFood.setPurchaseOrder();
        proFood.add(savedProcessedFood);


        ProcessedFood proFood2 = new ProcessedFood();
        proFood2.setName("burger");
        proFood2.setBarcode("123450000088");
        proFood2.setPrice(1.3);
        proFood2.setFoodType(FoodType.VEGAN);
        proFood2.setInStockQuantity(13);
        proFood.add(proFood2);

        ProcessedFood proFood3 = new ProcessedFood();
        proFood3.setName("fries");
        proFood3.setBarcode("1238989898045");
        proFood3.setPrice(1.3);
        proFood3.setFoodType(FoodType.NONVEGAN);
        proFood3.setInStockQuantity(13);

        proFood.add(proFood3);

        return proFood;
    }

    private Set<ProcessedFood> getProcessedFoodSet(){
        Set<ProcessedFood> processedFoods = new HashSet<>();
        processedFoods.addAll(getProcessedFood());

        return processedFoods;
    }

    private List<Stationary> getStationary() {

        List<Stationary> stationaries = new ArrayList<>();

        Stationary st1 = new Stationary();
        st1.setName("Glue");
        st1.setBarcode("189999892345");
        st1.setPrice(1.3);
        st1.setInStockQuantity(13);

        stationaries.add(st1);


        Stationary st2 = new Stationary();
        st2.setName("Pencil");
        st2.setBarcode("1234533333");
        st2.setPrice(1.3);
        st2.setInStockQuantity(13);

        stationaries.add(st2);


        Stationary st3 = new Stationary();
        st3.setName("Ink");
        st3.setBarcode("12346621115");
        st3.setPrice(1.3);
        st3.setInStockQuantity(13);

        stationaries.add(st3);


        Stationary st4 = new Stationary();
        st4.setName("Ruler");
        st4.setBarcode("12434456245");
        st4.setPrice(1.4);
        st4.setInStockQuantity(13);
        stationaries.add(st4);


        Stationary st5 = new Stationary();
        st5.setName("Eraser");
        st5.setBarcode("1254222002335");
        st5.setPrice(1.5);
        st5.setInStockQuantity(18);
        stationaries.add(st5);


        return stationaries;

    }

    private Set<Stationary> getStationarySet(){
        Set<Stationary> stationarys = new HashSet<>();
        stationarys.addAll(getStationary());

        return stationarys;
    }

    private List<PurchaseOrder> getPurchaseOrder(){
//        ProcessedFood savedProcessedFood = new ProcessedFood();
//        savedProcessedFood.setName("Chips");
//        savedProcessedFood.setBarcode("12345");
//        savedProcessedFood.setPrice(1.3);
//        savedProcessedFood.setFoodType(FoodType.VEGAN);
//        savedProcessedFood.setInStockQuantity(13);
        //savedProcessedFood.setPurchaseOrder(new PurchaseOrder());

//        FruitAndVege fav1 = new FruitAndVege();
//        fav1.setName("Banana");
//        fav1.setBarcode("12335663");
//        fav1.setInStockQuantity(5);
//        fav1.setPrice(2.99);
//
//        FruitAndVege fav2 = new FruitAndVege();
//        fav2.setName("Orange");
//        fav2.setBarcode("12233323");
//        fav2.setInStockQuantity(54);
//        fav2.setPrice(1.99);
//
//        Member m1 = new Member();
//        m1.setEmail("jd@gmail.com");
//        m1.setMemberNumber("jb11223");
//        m1.setFirstName("John");
//        m1.setLastName("Doe");
//        m1.setPhoneNumber("234-234-2222");
//        //m1.setPurchaseOrders(getPurchaseOrderSet());
//
        Member m2 = new Member();
        m2.setEmail("ml@gmail.com");
        m2.setMemberNumber("ml11223");
        m2.setFirstName("Mike");
        m2.setLastName("Long");
        m2.setPhoneNumber("334-234-2222");
        //m2.setPurchaseOrders(getPurchaseOrderSet());


        List<PurchaseOrder> purchaseOrders = new ArrayList<>();

        PurchaseOrder PO1 = new PurchaseOrder();
        PO1.setDateCreated(LocalDate.now());
        PO1.setPaymentType(PaymentType.CARD);
       // PO1.setMember(m2);
        //PO1.getTotalNumberOfItemsInPurchaseOrder();
        //PO1.setPurchaseOrderNumber("1233988393893893");
        //PO1.setFruitAndVeges(getFruitAndVegesSet());

        purchaseOrders.add(PO1);

        PurchaseOrder PO2 = new PurchaseOrder();
      //PO2.setMember(m2);
      //PO2.setMemberNumber();
        PO2.setDateCreated(LocalDate.now());
        PO2.setPaymentType(PaymentType.CARD);
        //PO2.getTotalNumberOfItemsInPurchaseOrder();
       // PO2.setReceiptNumber("13334988393893893");
//        PO2.getFruitAndVeges().add(fav1);
//        PO2.getFruitAndVeges().add(fav2);
       // PO2.setMemberNumber(m1.getMemberNumber());




//        proFood.add(savedProcessedFood);

        purchaseOrders.add(PO2);

        PurchaseOrder PO3 = new PurchaseOrder();
       //PO3.setMember(m2);
      // PO3.setMemberNumber();
        PO3.setDateCreated(LocalDate.now());
        PO3.setPaymentType(PaymentType.CASH);
        //PO3.getTotalNumberOfItemsInPurchaseOrder();
        //PO3.setPurchaseOrderNumber("004453333893");
        //PO3.setFruitAndVeges(getFruitAndVegesSet());
       // PO3.setProcessedFoods(getProcessedFoodSet());
       // PO3.setStationaries(getStationarySet());

        purchaseOrders.add(PO3);

        return purchaseOrders;
    }

    private Set<PurchaseOrder> getPurchaseOrderSet(){
        Set<PurchaseOrder> purchaseOrders = new HashSet<>();
        purchaseOrders.addAll(getPurchaseOrder());

        return purchaseOrders;
    }

    private List<Member> getMembers(){
        List<Member> members = new ArrayList<>();

        Member m1 = new Member();
        m1.setEmail("jd@gmail.com");
        m1.setMemberNumber("jb11223");
        m1.setFirstName("John");
        m1.setLastName("Doe");
        m1.setPhoneNumber("234-234-2222");
//        m1.addPurchaseOrder(new PurchaseOrder(LocalDate.now(),34,"004453333893",PaymentType.CASH,
//                getFruitAndVeges(), getStationary(),getProcessedFood()));
//        m1.addPurchaseOrder(new PurchaseOrder(LocalDate.now(),14,"177004453333893",PaymentType.CASH,
//                getFruitAndVeges(), getStationary(),getProcessedFood()));

        members.add(m1);


        Member m2 = new Member();
        m2.setEmail("ml@gmail.com");
        m2.setMemberNumber("ml11223");
        m2.setFirstName("Mike");
        m2.setLastName("Long");
        m2.setPhoneNumber("334-234-2222");

        //m2.setPurchaseOrders(getPurchaseOrderSet());

        members.add(m2);


        Member m3 = new Member();
        m3.setEmail("bs@gmail.com");
        m3.setMemberNumber("bs11223");
        m3.setFirstName("Ben");
        m3.setLastName("Skye");
        m3.setPhoneNumber("800-234-2222");
        //m3.setPurchaseOrders(getPurchaseOrderSet());

        members.add(m3);


        return members;
    }


}
