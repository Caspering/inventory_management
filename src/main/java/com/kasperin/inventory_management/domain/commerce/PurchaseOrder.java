package com.kasperin.inventory_management.domain.commerce;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.domain.Items.ProcessedFood;
import com.kasperin.inventory_management.domain.Items.Stationary;
import com.kasperin.inventory_management.domain.customer.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class PurchaseOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    @Column(name = "quantity")
    private Integer totalNumberOfItemsInPurchaseOrder;

    private String memberNumber;

    private String receiptNumber;

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @ManyToMany
    //@Size(min=1, message="You must have an Item in your purchase order")
    private List<FruitAndVege> fruitAndVeges = new ArrayList<>();

    @ManyToMany
    private List<Stationary> stationaries = new ArrayList<>();

    @ManyToMany//(cascade = CascadeType.REFRESH, mappedBy = "purchaseOrder")
    /*@JoinTable(name = "purchase_order_processed_food",
            joinColumns =  @JoinColumn (name = "purchase_order_id"),
            inverseJoinColumns = {@JoinColumn(name = "processed_food_id")}
    )*/
    private List<ProcessedFood> processedFoods = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Member member;

    @PrePersist
    void setDateCreatedAndMemberNumber(){
        this.dateCreated = LocalDate.now();
//        if (this.memberNumber!=null) {
//
// //           this.member=this.getMember(member);
////            setMember(memberRepository.findByMemberNumberIgnoreCase(this.getMemberNumber()));
////            this.member = memberRepository.findByMemberNumberIgnoreCase(this.getMemberNumber());
//        }
        this.totalNumberOfItemsInPurchaseOrder = countItemsInPurchaseOrder();
    }

    @Transient
    public int countItemsInPurchaseOrder() {
        return this.getNumberOfFruitAndVeges()
                + this.getNumberOfProcessedFoods()
                + this.getNumberOfStationarys();
    }

    @Transient
    public Double getTotalPurchaseOrderPrice() {
        return this.getTotalPurchaseOrderFruitAndVegePrice()
                + this.getTotalPurchaseOrderProcessedFoodPrice()
                + this.getTotalPurchaseOrderStationaryPrice();
    }

    @Transient
    @JsonIgnore
    public int getNumberOfProcessedFoods() {
        int add = 0;
        List<ProcessedFood> processedFoods = getProcessedFoods();
        for(ProcessedFood pf : processedFoods){
            add += pf.getInStockQuantity();
        }
        return add;
    }

    @Transient
    @JsonIgnore
    public int getNumberOfStationarys() {
        int add = 0;
       List<Stationary> stationaries = getStationaries();
       for(Stationary st : stationaries){
           add += st.getInStockQuantity();
       }
       return add;
    }

    @Transient
    @JsonIgnore
    public int getNumberOfFruitAndVeges() {
        int add = 0;
        List<FruitAndVege> fruitAndVeges = getFruitAndVeges();
        for(FruitAndVege fv : fruitAndVeges){
            add += fv.getInStockQuantity();
        }
        return add;
    }

    @Transient
    @JsonIgnore
    public Double getTotalPurchaseOrderFruitAndVegePrice() {
        double sum = 0D;
        List<FruitAndVege> fruitAndVeges = getFruitAndVeges();
        for (FruitAndVege fav : fruitAndVeges) {
            sum += fav.getTotalPrice();
        }
        return sum;
    }

    @Transient
    @JsonIgnore
    public Double getTotalPurchaseOrderStationaryPrice() {
        double sum = 0D;
        List<Stationary> stationarys = getStationaries();
        for (Stationary st : stationarys) {
            sum += st.getTotalPrice();
        }
        return sum;
    }

    @Transient
    @JsonIgnore
    public Double getTotalPurchaseOrderProcessedFoodPrice() {
        double sum = 0D;
        List<ProcessedFood> processedFoods = getProcessedFoods();
        for (ProcessedFood pf : processedFoods) {
            sum += pf.getTotalPrice();
        }
        return sum;
    }

    public void addProcessedFood(ProcessedFood processedFood){
        this.processedFoods.add(processedFood);
    }
    public void removeProcessedFood(ProcessedFood processedFood){
        processedFoods.remove(processedFood);
        // processedFood.setPurchaseOrder(null);
    }

    public void addStationary(Stationary stationary){
        this.stationaries.add(stationary);
    }
    public void removeStationary(Stationary stationary){
        stationaries.remove(stationary);
    }

    public void addFruitAndVege(FruitAndVege fruitAndVege){
        this.fruitAndVeges.add(fruitAndVege);
    }
    public void removeFruitAndVege(FruitAndVege fruitAndVege){
        this.fruitAndVeges.remove(fruitAndVege);
    }




    //      public PurchaseOrder addMember(Member member){
//        member.setPurchaseOrders();
//        this.fruitAndVeges.add(fruitAndVege);
//        return this;
//    }

    /*    public PurchaseOrder(LocalDate dateCreated, int totalNumberOfItemsInPurchaseOrder,
                         String receiptNumber,
                         PaymentType paymentType, Set<FruitAndVege> fruitAndVeges,
                         List<Stationary> stationaries, List<ProcessedFood> processedFoods) {
        this.dateCreated = dateCreated;
        this.totalNumberOfItemsInPurchaseOrder = totalNumberOfItemsInPurchaseOrder;
       // this.memberNumber = memberNumber;
        this.receiptNumber = receiptNumber;
        this.paymentType = paymentType;
        this.fruitAndVeges = fruitAndVeges;
        this.stationaries = stationaries;
        this.processedFoods = processedFoods;
    }*/

    /* public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
        member.setPurchaseOrders((Set<PurchaseOrder>) this);
    }*/

    /*public String getMemberNumber() {
        return member.getMemberNumber();
    }

    public void setMemberNumber() {
        this.memberNumber = getMemberNumber();
    }*/
}
