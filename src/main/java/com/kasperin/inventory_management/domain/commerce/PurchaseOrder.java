package com.kasperin.inventory_management.domain.commerce;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "PurchaseOrder")
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
public class PurchaseOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    private int TotalNumberOfItemsInPurchaseOrder;

    private String memberNumber;

    //@GeneratedValue
    private String receiptNumber;

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "purchase_order_fruit_and_vege",
                joinColumns =  @JoinColumn (name = "purchase_order_id"),
                inverseJoinColumns = {@JoinColumn(name = "fruit_and_vege_id")}
                )
    //@Size(min=1, message="You must have an Item in your purchase order")
    private Set<FruitAndVege> fruitAndVeges = new HashSet<>();

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "purchaseOrder")
    private List<Stationary> stationaries = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "purchaseOrder")
    private List<ProcessedFood> processedFoods = new ArrayList<>();

    @ManyToOne
    private Member member;

    @PrePersist
    void dateCreated() {
        this.dateCreated = LocalDate.now();
    }

    @Transient
    public Double getTotalPurchaseOrderFruitAndVegePrice() {
        double sum = 0D;
        Set<FruitAndVege> fruitAndVeges = getFruitAndVeges();
        for (FruitAndVege fav : fruitAndVeges) {
            sum += fav.getTotalPrice();
        }

        return sum;
    }

    @Transient
    public Double getTotalPurchaseOrderStationaryPrice() {
        double sum = 0D;
        List<Stationary> stationarys = getStationaries();
        for (Stationary st : stationarys) {
            sum += st.getTotalPrice();
        }
        return sum;
    }

    @Transient
    public Double getTotalPurchaseOrderProcessedFoodPrice() {
        double sum = 0D;
        List<ProcessedFood> processedFoods = getProcessedFoods();
        for (ProcessedFood pf : processedFoods) {
            sum += pf.getTotalPrice();
        }
        return sum;
    }

    @Transient
    public Double getTotalPurchaseOrderPrice() {
        return this.getTotalPurchaseOrderFruitAndVegePrice()
                + this.getTotalPurchaseOrderProcessedFoodPrice()
                + this.getTotalPurchaseOrderStationaryPrice();
    }

    @Transient
    public int getNumberOfProcessedFoods() {
        return this.processedFoods.size();
    }

    @Transient
    public int getNumberOfStationarys() {
        return this.stationaries.size();
    }

    @Transient
    public int getNumberOfFruitAndVeges() {
        return this.fruitAndVeges.size();
    }

    @Transient
    public int getTotalNumberOfItemsInPurchaseOrder() {
        return this.getNumberOfFruitAndVeges() + this.getNumberOfProcessedFoods()
                + this.getNumberOfStationarys();
    }



    public PurchaseOrder addProcessedFood(ProcessedFood processedFood){
        processedFood.setPurchaseOrder(this);
        this.processedFoods.add(processedFood);
        return this;
    }

    public PurchaseOrder addStationary(Stationary stationary){
        stationary.setPurchaseOrder(this);
        this.stationaries.add(stationary);
        return this;
    }









//      public PurchaseOrder addMember(Member member){
//        member.setPurchaseOrders();
//        this.fruitAndVeges.add(fruitAndVege);
//        return this;
//    }

   /* public PurchaseOrder addFruitAndVege(FruitAndVege fruitAndVege){
        fruitAndVege.setPurchaseOrder(this);
        this.fruitAndVeges.add(fruitAndVege);
        return this;
    }*/

//    public PurchaseOrder(LocalDate dateCreated, int quantity,
//                         String purchaseOrderNumber,
//                         PaymentType paymentType, List<FruitAndVege> fruitAndVeges,
//                         List<Stationary> stationaries, List<ProcessedFood> processedFoods) {
//        this.dateCreated = dateCreated;
//        this.quantity = quantity;
//        this.memberNumber = memberNumber;
//        this.purchaseOrderNumber = purchaseOrderNumber;
//        this.paymentType = paymentType;
//        this.fruitAndVeges = fruitAndVeges;
//        this.stationaries = stationaries;
//        this.processedFoods = processedFoods;
//    }

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
