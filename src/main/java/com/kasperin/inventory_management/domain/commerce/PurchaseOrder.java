package com.kasperin.inventory_management.domain.commerce;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kasperin.inventory_management.domain.Items.OrderedItem;
import com.kasperin.inventory_management.domain.customer.Member;
import com.kasperin.inventory_management.domain.enums.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
//@EntityListeners(PurchaseOrderEventHandler.class)
@Data
@Slf4j
@NoArgsConstructor
public class PurchaseOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    @Column(name = "quantity")
    private Integer totalNumberOfItems;

    private String memberNumber;

    private String receiptNumber;

    private Double totalPrice;

    private Double discountAmount;

    @Column(name = "discountedPrice")
    private Double totalPriceAfterDiscount;

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    /*@OneToMany//(cascade= {CascadeType.REMOVE}, fetch=FetchType.EAGER)
    private List<Item> items = new ArrayList<>();*/

    @OneToMany//(cascade= {CascadeType.REMOVE}, fetch=FetchType.EAGER)
    private List<OrderedItem> items = new ArrayList<>();


    @JsonIgnore
    @ManyToOne
    private Member member;

    @PrePersist
    void setDateCreatedAndMemberNumber(){
        this.dateCreated = LocalDate.now();
        this.totalNumberOfItems = countItems();
        this.receiptNumber=RandomStringUtils.randomNumeric(12);
        this.totalPriceAfterDiscount = getTotalPriceAfterDiscount();
        this.totalPrice = getTotalPrice();

       // this.setTotalPrice(this.getTotalPrice());
        //this.setTotalPriceAfterDiscount(getTotalPrice()-getDiscountAmount());
        //this.setDiscountAmount(this.getDiscountAmt());

        //=  UUID.fromString("Number").toString();//randomUUID().toString();
        log.info("This purchase order has been assigned receipt number: "+this.getReceiptNumber());
    }

    @Transient
    @JsonProperty(value = "Total number of Items")
    public int countItems(){
        int count = 0;
        List<OrderedItem> items = getItems();
        for(OrderedItem item : items){
            count += item.getQuantity();
        }
        return count;
    }

    //@JsonIgnore
    //@Transient
    public Double getTotalPrice(){
        double sum = 0;
        List<OrderedItem> items = getItems();
        for (OrderedItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    //@Transient
    public Double getTotalPriceAfterDiscount(){
        if (getDiscountAmount() != null)
        return getTotalPrice()-getDiscountAmount();
        else return null;
    }

   /* @Transient
    public Double getDiscountAmt(){
        PurchaseOrderServiceImpl.OrderForm.g form = null;
        double discountAmount = getTotalPrice() * form.discountAmount;
    }*/

    public void addItem(OrderedItem item){
        this.items.add(item);
    }
    public void removeItem(OrderedItem item){
        this.items.remove(item);
    }

    /*    @ManyToMany
    //@Size(min=1, message="You must have an Item in your purchase order")
    private List<FruitAndVege> fruitAndVeges = new ArrayList<>();

    @ManyToMany
    private List<Stationary> stationaries = new ArrayList<>();

    @ManyToMany
    private List<ProcessedFood> processedFoods = new ArrayList<>();*/
    /*@Transient
    public List<Object> items = new ArrayList<>();
    public List<Object> getItems(){
        List<Object> items = new ArrayList<>();
        items.add(getFruitAndVeges());
        items.add(getStationaries());
        items.add(getProcessedFoods());
        return items;
    }*/
    /*public Double getTotalPrice() {
        return this.getTotalFruitAndVegePrice()
                + this.getTotalProcessedFoodPrice()
                + this.getTotalStationaryPrice();
    }

    @JsonIgnore
    public int getNumberOfProcessedFoods() {
        int count = 0;
        List<ProcessedFood> processedFoods = getProcessedFoods();
        for(ProcessedFood pf : processedFoods){
            count += pf.getInStockQuantity();
        }
        return count;
    }*/
    /*@Transient
    public int countAllItems() {
        return this.getNumberOfFruitAndVeges()
                + this.getNumberOfProcessedFoods()
                + this.getNumberOfStationarys();
    }*/
    /*@JsonIgnore
    public Double getTotalStationaryPrice() {
        double sum = 0D;
        List<Stationary> stationarys = getStationaries();
        for (Stationary st : stationarys) {
            sum += st.getTotalPrice();
        }
        return sum;
    }

    @JsonIgnore
    public Double getTotalProcessedFoodPrice() {
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
    }*/
    /*@JsonIgnore
    public int getNumberOfStationarys() {
        int count = 0;
       List<Stationary> stationaries = getStationaries();
       for(Stationary st : stationaries){
           count += st.getInStockQuantity();
       }
       return count;
    }

    @JsonIgnore
    public int getNumberOfFruitAndVeges() {
        int count = 0;
        List<FruitAndVege> fruitAndVeges = getFruitAndVeges();
        for(FruitAndVege fv : fruitAndVeges){
            count += fv.getInStockQuantity();
        }
        return count;
    }*/
    /*public Double getTotalFruitAndVegePrice() {
        double sum = 0D;
        List<FruitAndVege> fruitAndVeges = getFruitAndVeges();
        for (FruitAndVege fav : fruitAndVeges) {
            sum += fav.getTotalPrice();
        }
        return sum;
    }*/
}
