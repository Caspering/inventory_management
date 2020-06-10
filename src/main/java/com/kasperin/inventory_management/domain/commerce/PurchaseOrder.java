package com.kasperin.inventory_management.domain.commerce;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.domain.Items.ProcessedFood;
import com.kasperin.inventory_management.domain.Items.Stationary;
import com.kasperin.inventory_management.domain.customer.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
//@RequiredArgsConstructor
public class PurchaseOrder implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    @Column(name = "quantity")
    private int totalNumberOfItemsInPurchaseOrder;

   // @ToString.Include
    private String memberNumber;

    //@GeneratedValue
    //@ToString.Include
    private String receiptNumber;

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @OneToMany//(cascade = CascadeType.REFRESH)
    @JoinTable(name = "purchase_order_fruit_and_vege",
                joinColumns =  @JoinColumn (name = "purchase_order_id"),
                inverseJoinColumns = {@JoinColumn(name = "fruit_and_vege_id")}
                )
    //@Size(min=1, message="You must have an Item in your purchase order")
    private List<FruitAndVege> fruitAndVeges = new ArrayList<>();

    @OneToMany//(cascade = CascadeType.ALL, mappedBy = "purchaseOrder")
    @JoinTable(name = "purchase_order_stationary",
            joinColumns =  @JoinColumn (name = "purchase_order_id"),
            inverseJoinColumns = {@JoinColumn(name = "stationary_id")}
    )
    private List<Stationary> stationaries = new ArrayList<>();

    @OneToMany//(cascade = CascadeType.REFRESH, mappedBy = "purchaseOrder")
    @JoinTable(name = "purchase_order_processed_food",
            joinColumns =  @JoinColumn (name = "purchase_order_id"),
            inverseJoinColumns = {@JoinColumn(name = "processed_food_id")}
    )
    private List<ProcessedFood> processedFoods = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    //@ToString.Include
    private Member member;


    @PrePersist
    void setDateCreatedAndMemberNumber(){
        this.dateCreated = LocalDate.now();
        if (this.member!=null)
        this.memberNumber = member.getMemberNumber();
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
